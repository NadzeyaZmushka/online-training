package com.epam.jwd.training.pool;

import com.epam.jwd.training.exception.CouldNotInitializeConnectionPoolException;

import java.sql.Connection;

/**
 * Connection pool interface (retrieving and returning connections).
 *
 * @author Nadzeya Zmushka
 */
public interface ConnectionPool {

    /**
     * Returns free connection from connection pool.
     *
     * @return fre {@link Connection}
     */
    Connection takeConnection();

    /**
     * Puts used connection to connection pool
     *
     * @param connection connection that has to be returned to connection pool
     */
    void releaseConnection(Connection connection);

    /**
     * Initialize connection pool
     *
     * @throws CouldNotInitializeConnectionPoolException when connection pool cannot be initialized
     */
    void init() throws CouldNotInitializeConnectionPoolException;

    /**
     * Stop connection pool
     */
    void destroy();

}
