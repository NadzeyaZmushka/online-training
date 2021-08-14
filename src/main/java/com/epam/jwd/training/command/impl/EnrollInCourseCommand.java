package com.epam.jwd.training.command.impl;

import com.epam.jwd.training.command.Command;
import com.epam.jwd.training.command.CommandResponse;
import com.epam.jwd.training.command.PagePath;
import com.epam.jwd.training.command.RequestAttribute;
import com.epam.jwd.training.command.RequestParameter;
import com.epam.jwd.training.command.SessionAttribute;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.entity.Course;
import com.epam.jwd.training.model.entity.User;
import com.epam.jwd.training.model.service.CourseService;
import com.epam.jwd.training.model.service.UserService;
import com.epam.jwd.training.model.service.impl.CourseServiceImpl;
import com.epam.jwd.training.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * The command enrolls user on course
 *
 * @author Nadzeya Zmushka
 */
public class EnrollInCourseCommand implements Command {

    public static final Logger LOGGER = LogManager.getLogger(EnrollInCourseCommand.class);

    private final CourseService courseService = CourseServiceImpl.getInstance();
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        String courseIdString = request.getParameter(RequestParameter.COURSE_ID);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(RequestAttribute.USER);
        CommandResponse response = new CommandResponse();
        boolean isCorrectDada = true;
        try {
            Long courseId = Long.valueOf(courseIdString);
            Optional<Course> courseOptional = courseService.findById(courseId);
            if (courseOptional.isPresent()) {
                boolean isHaveCourse = userService.isHaveCourse(user.getId(), courseId);
                if (!isHaveCourse) {
                    userService.enrollOnCourse(user, courseId);
                    session.setAttribute(SessionAttribute.USER, user);
                    response.setType(CommandResponse.Type.REDIRECT);
                    response.setPagePath(PagePath.PROFILE.getServletPath());
                } else {
                    session.setAttribute(SessionAttribute.ERROR_USER_HAVE_COURSE, true);
                    isCorrectDada = false;
                }

            } else {
                session.setAttribute(SessionAttribute.ERROR_COURSE_NOT_FOUND, true);
                response.setType(CommandResponse.Type.REDIRECT);
                response.setPagePath(PagePath.MAIN.getServletPath());
            }
            if (!isCorrectDada) {
                response.setPagePath(PagePath.COURSE.getServletPath() + courseId);
                response.setType(CommandResponse.Type.REDIRECT);
            }

        } catch (ServiceException e) {
            LOGGER.error(e);
            response.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return response;
    }

}
