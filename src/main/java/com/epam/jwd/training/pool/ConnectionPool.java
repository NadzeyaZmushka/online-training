package com.epam.jwd.training.pool;

import com.epam.jwd.training.exception.CouldNotInitializeConnectionPoolException;

import java.sql.Connection;

public interface ConnectionPool {

    Connection takeConnection();

    void releaseConnection(Connection connection);

    void init() throws CouldNotInitializeConnectionPoolException;

    void destroy();

}
