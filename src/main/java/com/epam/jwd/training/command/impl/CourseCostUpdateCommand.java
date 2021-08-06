package com.epam.jwd.training.command.impl;

import com.epam.jwd.training.command.Command;
import com.epam.jwd.training.command.CommandResponse;
import com.epam.jwd.training.command.PagePath;
import com.epam.jwd.training.command.RequestAttribute;
import com.epam.jwd.training.command.RequestParameter;
import com.epam.jwd.training.command.SessionAttribute;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.entity.Course;
import com.epam.jwd.training.model.service.CourseService;
import com.epam.jwd.training.model.service.impl.CourseServiceImpl;
import com.epam.jwd.training.validator.CourseValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

/**
 * The command updates course cost
 *
 * @author Nadzeya Zmushka
 */
public class CourseCostUpdateCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(CourseCostUpdateCommand.class);

    private final CourseService courseService = CourseServiceImpl.getInstance();
    private final CourseValidator courseValidator = CourseValidator.getInstance();

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        String courseIdString = request.getParameter(RequestParameter.COURSE_ID);
        String cost = request.getParameter(RequestParameter.COST);
        HttpSession session = request.getSession();
        CommandResponse response = new CommandResponse();
        boolean isCorrectData = true;
        try {
            Long courseId = Long.valueOf(courseIdString);
//            Optional<Course> courseOptional = courseService.findById(courseId);
//            if (courseOptional.isPresent()) {
            if (!courseValidator.isValidCost(cost)) {
                session.setAttribute(SessionAttribute.ERROR_COST, true);
                isCorrectData = false;
            }
            if (isCorrectData) {
                Course course = Course.builder()
                        .setId(courseId)
                        .setCost(BigDecimal.valueOf(Long.parseLong(cost)))
                        .build();
                courseService.updateCost(course);
            }
            response.setType(CommandResponse.Type.REDIRECT);
            response.setPagePath(PagePath.COURSE.getServletPath() + courseId);
//            }
//            else {
//                session.setAttribute(SessionAttribute.ERROR_COURSE_NOT_FOUND, true);
//                response.setType(CommandResponse.Type.REDIRECT);
//                response.setPagePath(PagePath.MAIN.getServletPath());
//            }
        } catch (ServiceException e) {
            LOGGER.error(e);
            response.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return response;
    }
}
