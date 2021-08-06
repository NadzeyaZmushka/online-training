package com.epam.jwd.training.command.impl;

import com.epam.jwd.training.command.Command;
import com.epam.jwd.training.command.CommandResponse;
import com.epam.jwd.training.command.PagePath;
import com.epam.jwd.training.command.RequestAttribute;
import com.epam.jwd.training.command.RequestParameter;
import com.epam.jwd.training.command.SessionAttribute;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.entity.Course;
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

/**
 * The command update teacher
 *
 * @author Nadzeya Zmushka
 */
public class CourseTeacherUpdateCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(CourseTeacherUpdateCommand.class);

    private final CourseService courseService = CourseServiceImpl.getInstance();
    private final TeacherService teacherService = TeacherServiceImpl.getInstance();
    private final CourseValidator courseValidator = CourseValidator.getInstance();

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        String courseIdString = request.getParameter(RequestParameter.COURSE_ID);
        String teacherName = request.getParameter(RequestParameter.TEACHER_NAME);
        String teacherSurname = request.getParameter(RequestParameter.TEACHER_SURNAME);
        HttpSession session = request.getSession();
        CommandResponse response = new CommandResponse();
        boolean isCorrectData = true;
        try {
            Long courseId = Long.valueOf(courseIdString);
            Optional<Teacher> teacherOptional = Optional.empty();
            if (!courseValidator.isValidNameAndSurname(teacherName, teacherSurname)) {
                session.setAttribute(SessionAttribute.ERROR_TEACHER_UPDATE, true);
                isCorrectData = false;
            }
            if (isCorrectData) {
                teacherOptional = teacherService.findByNameAndSurname(teacherName, teacherSurname);
                if (teacherOptional.isEmpty()) {
                    session.setAttribute(SessionAttribute.ERROR_TEACHER_NOT_FOUND, true);
                    isCorrectData = false;
                }
            }
            if (isCorrectData) {
                Course course = Course.builder()
                        .setId(courseId)
                        .setTeacher(teacherOptional.get())
                        .build();
                courseService.updateTeacher(course);
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
