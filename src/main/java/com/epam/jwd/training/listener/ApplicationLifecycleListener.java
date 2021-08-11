package com.epam.jwd.training.listener;

import com.epam.jwd.training.pool.ConcurrentConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Listener for destroy connection pool
 *
 * @author Nadzeya Zmushka
 */
@WebListener
public class ApplicationLifecycleListener implements ServletContextListener {

    private static final Logger LOGGER = LogManager.getLogger(ApplicationLifecycleListener.class);

    /**
     * Initialize connection poll when servlet start
     *
     * @param sce event
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConcurrentConnectionPool.getInstance().init();
        LOGGER.info("Pool is initialized");
    }

    /**
     * Destroy connection pool when servlet stops
     *
     * @param sce event
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConcurrentConnectionPool.getInstance().destroy();
        LOGGER.info("Pool is destroyed");
    }
}
