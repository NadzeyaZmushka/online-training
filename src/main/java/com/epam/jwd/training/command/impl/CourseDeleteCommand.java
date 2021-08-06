package com.epam.jwd.training.command.impl;

import com.epam.jwd.training.command.Command;
import com.epam.jwd.training.command.CommandResponse;
import com.epam.jwd.training.command.PagePath;
import com.epam.jwd.training.command.RequestAttribute;
import com.epam.jwd.training.command.RequestParameter;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.service.CourseService;
import com.epam.jwd.training.model.service.impl.CourseServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The command delete course.
 *
 * @author Nadzeya Zmushka
 */

public class CourseDeleteCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(CourseDeleteCommand.class);

    private final CourseService courseService = CourseServiceImpl.getInstance();

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        String courseIdString = request.getParameter(RequestParameter.COURSE_ID);
        CommandResponse response = new CommandResponse();
        try {
            Long courseId = Long.valueOf(courseIdString);
            courseService.delete(courseId);
            response.setType(CommandResponse.Type.REDIRECT);
            response.setPagePath(PagePath.MAIN.getServletPath());
        } catch (ServiceException e) {
            LOGGER.error(e);
            response.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return response;
    }

}
