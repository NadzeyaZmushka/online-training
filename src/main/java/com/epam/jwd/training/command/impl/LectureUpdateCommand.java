package com.epam.jwd.training.command.impl;

import com.epam.jwd.training.command.Command;
import com.epam.jwd.training.command.CommandResponse;
import com.epam.jwd.training.command.PagePath;
import com.epam.jwd.training.command.RequestAttribute;
import com.epam.jwd.training.command.RequestParameter;
import com.epam.jwd.training.command.SessionAttribute;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.dao.impl.CourseDaoImpl;
import com.epam.jwd.training.model.dao.impl.LectureDaoImpl;
import com.epam.jwd.training.model.entity.Course;
import com.epam.jwd.training.model.entity.Lecture;
import com.epam.jwd.training.model.service.CourseService;
import com.epam.jwd.training.model.service.LectureService;
import com.epam.jwd.training.model.service.impl.CourseServiceImpl;
import com.epam.jwd.training.model.service.impl.LectureServiceImpl;
import com.epam.jwd.training.validator.CourseValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * The command update lecture name
 *
 * @author Nadzeya Zmushka
 */
public class LectureUpdateCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(LectureUpdateCommand.class);

    private final LectureService lectureService = new LectureServiceImpl(new LectureDaoImpl());
    private final CourseService courseService = new CourseServiceImpl(new CourseDaoImpl());
    private final CourseValidator courseValidator = CourseValidator.getInstance();

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        String courseIdString = request.getParameter(RequestParameter.COURSE_ID);
        String lectureIdString = request.getParameter(RequestParameter.LECTURE_ID);
        String lectureName = new String(request.getParameter(RequestParameter.LECTURE).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        HttpSession session = request.getSession();
        CommandResponse response = new CommandResponse();
        boolean isCorrectData = true;
        try {
            Long courseId = Long.valueOf(courseIdString);
            Long lectureId = Long.valueOf(lectureIdString);
            if (!courseValidator.isValidLecture(lectureName)) {
                session.setAttribute(SessionAttribute.ERROR_MESSAGE, true);
                isCorrectData = false;
            }
            if (isCorrectData) {
                Optional<Course> courseOptional = courseService.findById(courseId);
                Optional<Lecture> lectureOptional = lectureService.findLectureByIdAndCourseId(lectureId, courseId);
                if (courseOptional.isEmpty()) {
                    session.setAttribute(SessionAttribute.ERROR_COURSE_NOT_FOUND, true);
                    response.setType(CommandResponse.Type.REDIRECT);
                    response.setPagePath(PagePath.MAIN.getServletPath());
                }
                if (lectureOptional.isEmpty()) {
                    session.setAttribute(SessionAttribute.ERROR_LECTURE_NOT_FOUND, true);
                    response.setType(CommandResponse.Type.REDIRECT);
                    response.setPagePath(PagePath.MAIN.getServletPath());
                }
            }
            if (isCorrectData) {
                Lecture lecture = Lecture.builder()
                        .setName(lectureName)
                        .setId(lectureId)
                        .build();
                lectureService.update(lecture);
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
