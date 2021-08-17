package com.epam.jwd.training.pool;

import com.epam.jwd.training.exception.CouldNotInitializeConnectionPoolException;
import com.epam.jwd.training.util.PropertiesReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Connection pool realized with two blocking queue {@link BlockingQueue} for free connections encapsulates.
 * Uses singleton pattern to return connection pool class instance.
 *
 * @author Nadzeya Zmushka
 */
public final class ConcurrentConnectionPool implements ConnectionPool {

    private static ConcurrentConnectionPool instance;

    private static final Logger LOGGER = LogManager.getLogger(ConcurrentConnectionPool.class);

    private static final String URL_KEY = "db.url";
    private static final String USER_KEY = "db.user";
    private static final String PASSWORD_KEY = "db.password";
    private static final String POOL_SIZE_KEY = "poolSize";
    private static final int DEFAULT_POOL_SIZE = 10;
    private static final Lock lock = new ReentrantLock();

    private static final String NOT_RECEIVED = "The connection is not received";
    private static final String NOT_RELEASE = "Connection wasn't release";
    private static final String CONNECTION_FAILED = "Connection failed";
    private static final String DRIVER_REGISTRATION_FAILED = "Driver registration failed";
    private static final String UNREGISTERING_DRIVER_FAILED = "Unregistering drivers failed ";

    private final Integer poolSize;
    private final AtomicBoolean initialized;
    private final BlockingQueue<ProxyConnection> availableConnections;
    private final BlockingQueue<ProxyConnection> takenConnections;

    private ConcurrentConnectionPool() {
        initialized = new AtomicBoolean(false);
        poolSize = Integer.valueOf(PropertiesReader.get(POOL_SIZE_KEY));
        availableConnections = new LinkedBlockingDeque<>();
        takenConnections = new LinkedBlockingDeque<>();
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
        ProxyConnection connection;
        try {
            connection = availableConnections.take();
            takenConnections.add(connection);
        } catch (InterruptedException e) {
            LOGGER.error(e);
            throw new CouldNotInitializeConnectionPoolException(NOT_RECEIVED, e);
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
                    throw new CouldNotInitializeConnectionPoolException(NOT_RELEASE, e);
                }
            }
        }
    }

    public void init() {
        if (initialized.compareAndSet(false, true)) {
            try {
                registerDrivers();
                int size = poolSize == null ? DEFAULT_POOL_SIZE : poolSize;
                for (int i = 0; i < size; i++) {
                    Connection connection = DriverManager.getConnection(PropertiesReader.get(URL_KEY),
                            PropertiesReader.get(USER_KEY),
                            PropertiesReader.get(PASSWORD_KEY)
                    );
                    availableConnections.add(new ProxyConnection(connection));
                }
            } catch (SQLException e) {
                LOGGER.fatal(e.getMessage());
                initialized.set(false);
                throw new CouldNotInitializeConnectionPoolException(CONNECTION_FAILED, e);
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
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            LOGGER.error(e);
            initialized.set(false);
            throw new CouldNotInitializeConnectionPoolException(DRIVER_REGISTRATION_FAILED, e);
        }
    }

    private void deregisterDrivers() {
        final Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            try {
                DriverManager.deregisterDriver(drivers.nextElement());
            } catch (SQLException e) {
                LOGGER.error(UNREGISTERING_DRIVER_FAILED + e.getMessage());
            }
        }
    }

}
