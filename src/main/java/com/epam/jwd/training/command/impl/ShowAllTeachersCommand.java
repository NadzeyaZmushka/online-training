package com.epam.jwd.training.command.impl;

import com.epam.jwd.training.command.Command;
import com.epam.jwd.training.command.CommandResponse;
import com.epam.jwd.training.command.PagePath;
import com.epam.jwd.training.command.RequestAttribute;
import com.epam.jwd.training.command.SessionAttribute;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.entity.Teacher;
import com.epam.jwd.training.model.service.TeacherService;
import com.epam.jwd.training.model.service.impl.TeacherServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowAllTeachersCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ShowAllTeachersCommand.class);

    private final TeacherService teacherService = TeacherServiceImpl.getInstance();

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        CommandResponse response = new CommandResponse();
        try {
            List<Teacher> teachers = teacherService.findAll();
            session.setAttribute(SessionAttribute.ALL_TEACHERS, teachers);
            response.setType(CommandResponse.Type.REDIRECT);
            response.setPagePath(PagePath.PROFILE.getServletPath());
        } catch (ServiceException e) {
            LOGGER.error(e);
            response.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return response;
    }

}
