package com.epam.jwd.training.command.impl;

import com.epam.jwd.training.command.Command;
import com.epam.jwd.training.command.CommandResponse;
import com.epam.jwd.training.command.PagePath;
import com.epam.jwd.training.command.RequestAttribute;
import com.epam.jwd.training.command.RequestParameter;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.dao.impl.UserDaoImpl;
import com.epam.jwd.training.model.entity.User;
import com.epam.jwd.training.model.service.UserService;
import com.epam.jwd.training.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UnsubscribeFromCourseCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(UnsubscribeFromCourseCommand.class);
    private final UserService userService = new UserServiceImpl(new UserDaoImpl());


    @Override
    public CommandResponse execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        CommandResponse response = new CommandResponse();
        String courseIdString = request.getParameter(RequestParameter.COURSE_ID);
        User userSession = (User) session.getAttribute(RequestAttribute.USER);
        try {
            Long courseId = Long.valueOf(courseIdString);
            userService.unEnrollOnCourse(userSession, courseId);
            response.setPagePath(PagePath.PROFILE.getServletPath());
        } catch (ServiceException e) {
            LOGGER.error(e);
            response.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return response;
    }
}
