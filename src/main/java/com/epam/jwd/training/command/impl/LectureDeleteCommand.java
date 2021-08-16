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
import com.epam.jwd.training.model.service.LectureService;
import com.epam.jwd.training.model.service.impl.CourseServiceImpl;
import com.epam.jwd.training.model.service.impl.LectureServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * The command delete lecture
 *
 * @author Nadzeya Zmushka
 */
public class LectureDeleteCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(LectureDeleteCommand.class);

    private final CourseService courseService = new CourseServiceImpl(new CourseDaoImpl());
    private final LectureService lectureService = LectureServiceImpl.getInstance();

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        String lectureIdString = request.getParameter(RequestParameter.LECTURE_ID);
        String courseIdString = request.getParameter(RequestParameter.COURSE_ID);
        HttpSession session = request.getSession();
        CommandResponse response = new CommandResponse();
        boolean isCorrectData = true;
        try {
            Long lectureId = Long.valueOf(lectureIdString);
            Long courseId = Long.valueOf(courseIdString);
            Optional<Course> courseOptional = courseService.findById(courseId);
            if (courseOptional.isEmpty()) {
                session.setAttribute(SessionAttribute.ERROR_COURSE_NOT_FOUND, true);
                response.setType(CommandResponse.Type.REDIRECT);
                response.setPagePath(PagePath.MAIN.getServletPath());
                isCorrectData = false;
            }
            if (isCorrectData) {
                lectureService.delete(lectureId);
                response.setType(CommandResponse.Type.REDIRECT);
                response.setPagePath(PagePath.COURSE.getServletPath() + courseId);
            }
        } catch (ServiceException e) {
            LOGGER.error(e);
            response.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return response;
    }
}
