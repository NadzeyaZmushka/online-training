package com.epam.jwd.training.pool;

import com.epam.jwd.training.entity.ApplicationProperties;
import com.epam.jwd.training.exception.CouldNotInitializeConnectionPoolException;
import com.epam.jwd.training.util.PropertiesReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Enumeration;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentConnectionPool implements ConnectionPool {

    private static ConcurrentConnectionPool instance;

    private static final Logger LOGGER = LogManager.getLogger(ConcurrentConnectionPool.class);

    private static final Lock lock = new ReentrantLock();

    private final int connectionPoolSize;
//    private final int MAX_CONNECTIONS_AMOUNT = 32;
//    private final int CONNECTIONS_GROW_FACTOR = 4;

    private final ApplicationProperties applicationProperties;
    private final AtomicBoolean initialized;
    private final BlockingQueue<ProxyConnection> availableConnections;
    private final Queue<ProxyConnection> takenConnections;


    private ConcurrentConnectionPool() {
        initialized = new AtomicBoolean(false);
        applicationProperties = PropertiesReader.getInstance().loadProperties();
        connectionPoolSize = applicationProperties.getPoolSize();
        availableConnections = new LinkedBlockingDeque<>(connectionPoolSize);
        takenConnections = new ArrayDeque<>();
    }


    public static ConcurrentConnectionPool getInstance() {
        if (instance == null) {
            lock.lock();
            if (instance == null) {
                instance = new ConcurrentConnectionPool();
            }
            lock.unlock();
        }
        return instance;
    }

    public Connection takeConnection() {
        ProxyConnection proxyConnection;
        proxyConnection = availableConnections.poll();
        takenConnections.add(proxyConnection);
        return proxyConnection;
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            if (connection instanceof ProxyConnection) {
                takenConnections.remove(connection);
                availableConnections.offer((ProxyConnection) connection);
            }
        }
    }

    public void init() throws CouldNotInitializeConnectionPoolException {
        if (initialized.compareAndSet(false, true)) {
            registerDrivers();
            try {
                for (int i = 0; i < connectionPoolSize; i++) {
                    Connection connection = DriverManager.getConnection(applicationProperties.getUrl(), applicationProperties.getUsername(),
                            applicationProperties.getPassword());
                    availableConnections.offer(new ProxyConnection(connection));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                initialized.set(false);
                throw new CouldNotInitializeConnectionPoolException("failed to open connection", e);
            }
        }
    }

    public void destroy() {
        if (initialized.compareAndSet(true, false)) {
            for (ProxyConnection conn : availableConnections) {
                try {
                    conn.realClose();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            deregisterDrivers();
        }
    }

    private void registerDrivers() throws CouldNotInitializeConnectionPoolException {
        try {
            DriverManager.registerDriver(DriverManager.getDriver(applicationProperties.getUrl()));
        } catch (SQLException e) {
            e.printStackTrace();
            initialized.set(false);
            throw new CouldNotInitializeConnectionPoolException("driver registration failed", e);
        }
    }

    private void deregisterDrivers() {
        final Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            try {
                DriverManager.deregisterDriver(drivers.nextElement());
            } catch (SQLException e) {
                LOGGER.error("unregistering drivers failed");
            }
        }
    }

}
