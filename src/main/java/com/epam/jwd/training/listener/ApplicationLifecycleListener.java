package com.epam.jwd.training.listener;

import com.epam.jwd.training.pool.ConcurrentConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationLifecycleListener implements ServletContextListener {

    private static final Logger LOGGER = LogManager.getLogger(ApplicationLifecycleListener.class);

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConcurrentConnectionPool.getInstance().destroy();
        LOGGER.info("Pool is destroyed");
    }
}
