package com.epam.jwd.training.pool;

import com.epam.jwd.training.exception.CouldNotInitializeConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentConnectionPool implements ConnectionPool {

    private static final Logger LOGGER = LogManager.getLogger(ConcurrentConnectionPool.class);

    private static ConcurrentConnectionPool instance;
    private static final Lock lock = new ReentrantLock();

    private static final int INIT_CONNECTIONS_AMOUNT = 8;
    private static final int MAX_CONNECTIONS_AMOUNT = 32;
    private static final int CONNECTIONS_GROW_FACTOR = 4;

    private final AtomicBoolean initialized;
    private final Queue<ProxyConnection> availableConnections;
    private AtomicInteger connectionsOpened;

    private ConcurrentConnectionPool() {
        initialized = new AtomicBoolean(false);
        availableConnections = new ArrayDeque<>();
        connectionsOpened = new AtomicInteger(0);
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
        return null;
    }

    public void releaseConnection(Connection connection) {

    }

    public void init() throws CouldNotInitializeConnectionPoolException {

    }

    public void destroy() {

    }

}
