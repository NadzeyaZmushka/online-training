package com.epam.jwd.training.command.impl;

import com.epam.jwd.training.command.Command;
import com.epam.jwd.training.command.CommandResponse;
import com.epam.jwd.training.command.PagePath;
import com.epam.jwd.training.command.RequestAttribute;
import com.epam.jwd.training.command.RequestParameter;
import com.epam.jwd.training.command.SessionAttribute;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.entity.Teacher;
import com.epam.jwd.training.model.service.CourseService;
import com.epam.jwd.training.model.service.TeacherService;
import com.epam.jwd.training.model.service.impl.CourseServiceImpl;
import com.epam.jwd.training.model.service.impl.TeacherServiceImpl;
import com.epam.jwd.training.validator.CourseValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class CourseAddCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(CourseAddCommand.class);

    private final CourseService courseService = CourseServiceImpl.getInstance();
    private final CourseValidator courseValidator = CourseValidator.getInstance();
    private final TeacherService teacherService = TeacherServiceImpl.getInstance();

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        String courseName = request.getParameter(RequestParameter.COURSE_NAME);
        String description = request.getParameter(RequestParameter.DESCRIPTION);
        String hours = request.getParameter(RequestParameter.HOURS);
        String name = request.getParameter(RequestParameter.NAME);
        String surname = request.getParameter(RequestParameter.SURNAME);
        String startCourse = request.getParameter(RequestParameter.START_COURSE);
        String endCourse = request.getParameter(RequestParameter.END_COURSE);
        String cost = request.getParameter(RequestParameter.COST);
        HttpSession session = request.getSession();
        CommandResponse response = new CommandResponse();
        boolean isCorrectData = true;
        try {
            if (!courseValidator.isValidName(courseName)) {
                session.setAttribute(SessionAttribute.ERROR_IS_NOT_VALID_COURSE_NAME, true);
                isCorrectData = false;
            }
            if (!courseValidator.isValidDescription(description)) {
                request.setAttribute(RequestAttribute.ERROR_DESCRIPTION_ADD, true);
                isCorrectData = false;
            }
            if (!courseValidator.isValidHours(hours)) {
                request.setAttribute(RequestAttribute.ERROR_HOURS_ADD, true);
                isCorrectData = false;
            }
            if (!courseValidator.isValidNameAndSurname(name, surname)) {
                request.setAttribute(RequestAttribute.ERROR_NAME_AND_SURNAME_ADD, true);
                isCorrectData = false;
            }
            if (!courseValidator.isValidCost(cost)) {
                request.setAttribute(RequestAttribute.ERROR_COST_ADD, true);
                isCorrectData = false;
            }
            if (!courseValidator.isValidDate(startCourse, endCourse)) {
                request.setAttribute(RequestAttribute.ERROR_START_AND_END_COURSE_ADD, true);
                isCorrectData = false;
            }
            Optional<Teacher> teacherOptional = teacherService.findByNameAndSurname(name, surname);
            if (teacherOptional.isEmpty()) {
                request.setAttribute(RequestAttribute.ERROR_TEACHER_NOT_FOUND_ADD, true);
                isCorrectData = false;
            }
            if (isCorrectData) {
                courseService.save(courseName, description, hours, startCourse, endCourse, cost, teacherOptional.get());
                response.setType(CommandResponse.Type.REDIRECT);
                response.setPagePath(PagePath.MAIN.getServletPath());
            }
            else {
                request.setAttribute(RequestAttribute.DESCRIPTION, description);
                request.setAttribute(RequestAttribute.HOURS, hours);
                request.setAttribute(RequestAttribute.NAME, name);
                request.setAttribute(RequestAttribute.SURNAME, surname);
                request.setAttribute(RequestAttribute.START_COURSE, startCourse);
                request.setAttribute(RequestAttribute.END_COURSE, endCourse);
                request.setAttribute(RequestAttribute.COST, cost);
                response.setPagePath(PagePath.COURSE.getServletPath());
            }
        } catch (ServiceException e) {
            LOGGER.error(e);
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
            response.setPagePath(PagePath.ERROR_500.getDirectUrl());
        }
        return response;
    }

}
