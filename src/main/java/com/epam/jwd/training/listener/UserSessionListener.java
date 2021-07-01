package com.epam.jwd.training.listener;

import com.epam.jwd.training.command.PagePath;
import com.epam.jwd.training.command.SessionAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class UserSessionListener implements HttpSessionListener {

    private static final Logger LOGGER = LogManager.getLogger(UserSessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.INDEX.getDirectUrl());
        LOGGER.info("Session created");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        LOGGER.debug("Session destroyed");
    }
}
