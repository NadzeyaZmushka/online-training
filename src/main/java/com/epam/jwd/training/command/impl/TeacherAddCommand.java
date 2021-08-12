package com.epam.jwd.training.command.impl;

import com.epam.jwd.training.command.Command;
import com.epam.jwd.training.command.CommandResponse;
import com.epam.jwd.training.command.PagePath;
import com.epam.jwd.training.command.RequestAttribute;
import com.epam.jwd.training.command.RequestParameter;
import com.epam.jwd.training.command.SessionAttribute;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.entity.Teacher;
import com.epam.jwd.training.model.service.TeacherService;
import com.epam.jwd.training.model.service.impl.TeacherServiceImpl;
import com.epam.jwd.training.validator.UserAndTeacherValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * The command add new teacher
 *
 * @author Nadzeya Zmushka
 */
public class TeacherAddCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(TeacherAddCommand.class);

    private final TeacherService teacherService = TeacherServiceImpl.getInstance();
    private final UserAndTeacherValidator teacherValidator = UserAndTeacherValidator.getInstance();

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        String name = request.getParameter(RequestParameter.TEACHER_NAME);
        String surname = request.getParameter(RequestParameter.TEACHER_SURNAME);
        HttpSession session = request.getSession();
        CommandResponse response = new CommandResponse();
        boolean isCorrectData = true;
        try {
            if (!teacherValidator.isValidNameAndSurname(name, surname)) {
                session.setAttribute(SessionAttribute.ERROR_TEACHER_ADD, true);
                isCorrectData = false;
            }
            if (isCorrectData) {
                Optional<Teacher> teacherOptional = teacherService.findByNameAndSurname(name, surname);
                if (teacherOptional.isPresent()) {
                    session.setAttribute(SessionAttribute.ERROR_TEACHER_ADD, true);
                    isCorrectData = false;
                }
                if (isCorrectData) {
                    Teacher teacher = Teacher.builder()
                            .setName(name)
                            .setSurname(surname).build();
                    teacherService.save(teacher);
                }
                response.setType(CommandResponse.Type.REDIRECT);
                response.setPagePath(PagePath.SHOW_ALL_TEACHERS.getServletPath());
            }
        } catch (ServiceException e) {
            LOGGER.error(e);
            response.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e);
        }
        return response;
    }
}
