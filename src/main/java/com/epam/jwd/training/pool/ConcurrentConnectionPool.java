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

    private static final Integer DEFAULT_POOL_SIZE = 10;

    private static final Lock lock = new ReentrantLock();

    private final Integer connectionPoolSize;

    private final ApplicationProperties applicationProperties;
    private final AtomicBoolean initialized;
    private final BlockingQueue<ProxyConnection> availableConnections;
    private final Queue<ProxyConnection> takenConnections;


    private ConcurrentConnectionPool() {
        initialized = new AtomicBoolean(false);
        applicationProperties = PropertiesReader.getInstance().loadProperties();
        connectionPoolSize = applicationProperties.getPoolSize();
        availableConnections = new LinkedBlockingDeque<>();
        takenConnections = new ArrayDeque<>();
        init();
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
        ProxyConnection connection = null;
        try {
            connection = availableConnections.take();
            takenConnections.add(connection);
        } catch (InterruptedException e) {
            LOGGER.error("The connection is not received " + e);
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            if (connection instanceof ProxyConnection) {
                takenConnections.remove(connection);
                try {
                    availableConnections.put((ProxyConnection) connection);
                } catch (InterruptedException e) {
                    LOGGER.error(e);
                    throw new CouldNotInitializeConnectionPoolException("Connection wasn't release", e);
                }
            }
        }
    }

    public void init() {
        if (initialized.compareAndSet(false, true)) {
            try {
                registerDrivers();
                Integer size = connectionPoolSize == null ? DEFAULT_POOL_SIZE : connectionPoolSize;
                for (int i = 0; i < size; i++) {
                    Connection connection = DriverManager.getConnection(applicationProperties.getUrl(),
                            applicationProperties.getUsername(),
                            applicationProperties.getPassword()
                    );
                    availableConnections.add(new ProxyConnection(connection));
                }
            } catch (SQLException e) {
                LOGGER.fatal(e.getMessage());
                initialized.set(false);
                throw new CouldNotInitializeConnectionPoolException("Connection failed...", e);
            }
        }
    }

    public void destroy() {
        if (initialized.compareAndSet(true, false)) {
            for (ProxyConnection conn : availableConnections) {
                try {
                    conn.realClose();
                } catch (SQLException e) {
                    LOGGER.error("The pool was not destroyed " + e.getMessage());
                }
            }
            deregisterDrivers();
        }
    }

    private void registerDrivers() throws CouldNotInitializeConnectionPoolException {
        try {
            DriverManager.registerDriver(DriverManager.getDriver(applicationProperties.getUrl()));
        } catch (SQLException e) {
            LOGGER.error(e);
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
                LOGGER.error("unregistering drivers failed " + e.getMessage());
            }
        }
    }

}
