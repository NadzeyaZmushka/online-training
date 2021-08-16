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
import com.epam.jwd.training.model.entity.Lecture;
import com.epam.jwd.training.model.service.CourseService;
import com.epam.jwd.training.model.service.LectureService;
import com.epam.jwd.training.model.service.impl.CourseServiceImpl;
import com.epam.jwd.training.model.service.impl.LectureServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

/**
 * The command opens course
 *
 * @author Nadzeya Zmushka
 */
public class CourseCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(CourseCommand.class);

    private final LectureService lectureService = LectureServiceImpl.getInstance();
    private final CourseService courseService = new CourseServiceImpl(new CourseDaoImpl());

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        String courseIdString = request.getParameter(RequestParameter.COURSE_ID);
        HttpSession session = request.getSession();
        CommandResponse response = new CommandResponse();
        try {
            Long courseId = Long.valueOf(courseIdString);
            Optional<Course> courseOptional = courseService.findById(courseId);
            if (courseOptional.isPresent()) {
                request.setAttribute(RequestAttribute.COURSE, courseOptional.get());
                List<Lecture> lectures = lectureService.findAllLecturesByCourseId(courseId);
                if (!lectures.isEmpty()) {
                    request.setAttribute(RequestAttribute.LECTURES, lectures);
                }
                request.setAttribute(RequestAttribute.COURSE_ID, courseOptional.get().getId());
                response.setPagePath(PagePath.COURSE.getDirectUrl());
                session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.COURSE.getServletPath() + courseId);
            } else {
                session.setAttribute(SessionAttribute.ERROR_COURSE_NOT_FOUND, true);
                response.setType(CommandResponse.Type.REDIRECT);
                response.setPagePath(PagePath.MAIN.getServletPath());
            }
        } catch (ServiceException e) {
            LOGGER.error(e);
            response.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }

        return response;
    }
}
