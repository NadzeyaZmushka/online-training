package com.epam.jwd.training.command.impl;

import com.epam.jwd.training.command.Command;
import com.epam.jwd.training.command.CommandResponse;
import com.epam.jwd.training.command.PagePath;
import com.epam.jwd.training.command.RequestAttribute;
import com.epam.jwd.training.command.SessionAttribute;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.entity.Course;
import com.epam.jwd.training.model.service.CourseService;
import com.epam.jwd.training.model.service.impl.CourseServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The command opens main page
 *
 * @author Nadzeya Zmushka
 */
public class MainCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(MainCommand.class);

    private final CourseService courseService = CourseServiceImpl.getInstance();

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        CommandResponse commandResponse = new CommandResponse();
        HttpSession session = request.getSession();
        try {
            List<Course> courses = courseService.findAll();
            request.setAttribute(RequestAttribute.COURSES, courses);
            commandResponse.setPagePath(PagePath.MAIN.getDirectUrl());
            session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.MAIN.getServletPath());
        } catch (ServiceException e) {
            LOGGER.error(e);
            commandResponse.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return commandResponse;
    }
}
