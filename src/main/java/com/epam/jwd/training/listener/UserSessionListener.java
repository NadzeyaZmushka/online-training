package com.epam.jwd.training.listener;

import com.epam.jwd.training.command.PagePath;
import com.epam.jwd.training.command.SessionAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Listener for create and destroy session
 *
 * @author Nadzeya Zmushka
 */
@WebListener
public class UserSessionListener implements HttpSessionListener {

    private static final Logger LOGGER = LogManager.getLogger(UserSessionListener.class);

    private static final String SESSION_CREATE = "Session created";
    private static final String SESSION_DESTROY = "Session destroyed";

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.INDEX.getDirectUrl());
        LOGGER.debug(SESSION_CREATE);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        LOGGER.debug(SESSION_DESTROY);
    }

}
