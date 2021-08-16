package com.epam.jwd.training.command.impl;

import com.epam.jwd.training.command.Command;
import com.epam.jwd.training.command.CommandResponse;
import com.epam.jwd.training.command.PagePath;
import com.epam.jwd.training.command.RequestAttribute;
import com.epam.jwd.training.command.RequestParameter;
import com.epam.jwd.training.command.SessionAttribute;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.dao.impl.CourseDaoImpl;
import com.epam.jwd.training.model.entity.Course;
import com.epam.jwd.training.model.service.CourseService;
import com.epam.jwd.training.model.service.impl.CourseServiceImpl;
import com.epam.jwd.training.validator.CourseValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;

/**
 * The command updates start and end of course.
 *
 * @author Nadzeya Zmushka
 */
public class CourseDateUpdateCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(CourseDateUpdateCommand.class);

    private final CourseService courseService = new CourseServiceImpl(new CourseDaoImpl());
    private final CourseValidator courseValidator = CourseValidator.getInstance();

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        String courseIdString = request.getParameter(RequestParameter.COURSE_ID);
        String start = request.getParameter(RequestParameter.START);
        String end = request.getParameter(RequestParameter.END);
        HttpSession session = request.getSession();
        CommandResponse response = new CommandResponse();
        boolean isCorrectData = true;
        try {
            Long courseId = Long.valueOf(courseIdString);
            if (!courseValidator.isValidDate(start, end)) {
                session.setAttribute(SessionAttribute.ERROR_START_END_UPDATE, true);
                isCorrectData = false;
            }
            if (isCorrectData) {
                Course course = Course.builder()
                        .setId(courseId)
                        .setStartCourse(Date.valueOf(LocalDate.parse(start)))
                        .setEndCourse(Date.valueOf(LocalDate.parse(end)))
                        .build();
                courseService.updateDate(course);
            }
            response.setType(CommandResponse.Type.REDIRECT);
            response.setPagePath(PagePath.COURSE.getServletPath() + courseId);
        } catch (ServiceException e) {
            LOGGER.error(e);
            response.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return response;
    }

}
