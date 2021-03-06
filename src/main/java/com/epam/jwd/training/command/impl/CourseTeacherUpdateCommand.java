package com.epam.jwd.training.command.impl;

import com.epam.jwd.training.command.Command;
import com.epam.jwd.training.command.CommandResponse;
import com.epam.jwd.training.command.PagePath;
import com.epam.jwd.training.command.RequestAttribute;
import com.epam.jwd.training.command.RequestParameter;
import com.epam.jwd.training.command.SessionAttribute;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.dao.impl.CourseDaoImpl;
import com.epam.jwd.training.model.dao.impl.TeacherDaoImpl;
import com.epam.jwd.training.model.entity.Course;
import com.epam.jwd.training.model.entity.Teacher;
import com.epam.jwd.training.model.service.CourseService;
import com.epam.jwd.training.model.service.TeacherService;
import com.epam.jwd.training.model.service.impl.CourseServiceImpl;
import com.epam.jwd.training.model.service.impl.TeacherServiceImpl;
import com.epam.jwd.training.validator.UserAndTeacherValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * The command update teacher
 *
 * @author Nadzeya Zmushka
 */
public class CourseTeacherUpdateCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(CourseTeacherUpdateCommand.class);

    private final CourseService courseService = new CourseServiceImpl(new CourseDaoImpl());
    private final TeacherService teacherService = new TeacherServiceImpl(new TeacherDaoImpl());
    private final UserAndTeacherValidator validator = UserAndTeacherValidator.getInstance();

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        String courseIdString = request.getParameter(RequestParameter.COURSE_ID);
        String teacherName = new String(request.getParameter(RequestParameter.TEACHER_NAME).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String teacherSurname = new String(request.getParameter(RequestParameter.TEACHER_SURNAME).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        HttpSession session = request.getSession();
        CommandResponse response = new CommandResponse();
        boolean isCorrectData = true;
        try {
            Long courseId = Long.valueOf(courseIdString);
            Optional<Teacher> teacherOptional = Optional.empty();
            if (!validator.isValidNameAndSurname(teacherName, teacherSurname)) {
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
            LOGGER.error(e.getMessage());
            response.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return response;
    }

}
