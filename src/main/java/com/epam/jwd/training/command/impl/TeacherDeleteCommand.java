package com.epam.jwd.training.command.impl;

import com.epam.jwd.training.command.Command;
import com.epam.jwd.training.command.CommandResponse;
import com.epam.jwd.training.command.PagePath;
import com.epam.jwd.training.command.RequestAttribute;
import com.epam.jwd.training.command.RequestParameter;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.dao.impl.TeacherDaoImpl;
import com.epam.jwd.training.model.service.TeacherService;
import com.epam.jwd.training.model.service.impl.TeacherServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The command delete teacher
 *
 * @author Nadzeya Zmushka
 */
public class TeacherDeleteCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(TeacherDeleteCommand.class);

    private final TeacherService teacherService = new TeacherServiceImpl(new TeacherDaoImpl());

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        String teacherIdString = request.getParameter(RequestParameter.TEACHER_ID);
        CommandResponse response = new CommandResponse();
        try {
            Long teacherId = Long.valueOf(teacherIdString);
            teacherService.delete(teacherId);
            response.setType(CommandResponse.Type.REDIRECT);
            response.setPagePath(PagePath.SHOW_ALL_TEACHERS.getServletPath());
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            response.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return response;
    }

}
