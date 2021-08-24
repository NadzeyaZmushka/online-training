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
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * The command update course name
 *
 * @author Nadzeya Zmushka
 */
public class CourseUpdateCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(CourseUpdateCommand.class);

    private final CourseService courseService = new CourseServiceImpl(new CourseDaoImpl());
    private final CourseValidator courseValidator = CourseValidator.getInstance();

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        String courseIdString = request.getParameter(RequestParameter.COURSE_ID);
        String courseName = new String(request.getParameter(RequestParameter.COURSE_NAME).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        HttpSession session = request.getSession();
        CommandResponse response = new CommandResponse();
        boolean isCorrectData = true;
        try {
            Long courseId = Long.valueOf(courseIdString);
            if (!courseValidator.isValidName(courseName)) {
                session.setAttribute(SessionAttribute.ERROR_IS_NOT_VALID_COURSE_NAME, true);
                isCorrectData = false;
            }
            if (isCorrectData) {
                Optional<Course> courseOptional = courseService.findById(courseId);
                if (courseOptional.isEmpty()) {
                    session.setAttribute(SessionAttribute.ERROR_COURSE_NOT_FOUND, true);
                    isCorrectData = false;
                }
            }
            if (isCorrectData) {
                Course course = Course.builder()
                        .setId(courseId)
                        .setName(courseName)
                        .build();
                courseService.updateCourseName(course);
            }
            response.setType(CommandResponse.Type.REDIRECT);
            response.setPagePath(PagePath.COURSE.getServletPath() + courseId);
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            response.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return response;
    }

}
