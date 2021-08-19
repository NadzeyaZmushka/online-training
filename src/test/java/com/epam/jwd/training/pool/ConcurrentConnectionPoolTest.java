package com.epam.jwd.training.pool;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.assertNotNull;

public class ConcurrentConnectionPoolTest {

    private ConnectionPool pool;

    @Before
    public void init() {
        pool = ConcurrentConnectionPool.getInstance();
        pool.init();
    }

    @After
    public void tearDown() {
        pool.destroy();
    }

    @Test
    public void test_takeConnection() {
        Connection connection = pool.takeConnection();
        assertNotNull(connection);
    }

}