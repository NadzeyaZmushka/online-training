package com.epam.jwd.training.command.impl;

import com.epam.jwd.training.command.Command;
import com.epam.jwd.training.command.CommandResponse;
import com.epam.jwd.training.command.PagePath;
import com.epam.jwd.training.command.RequestAttribute;
import com.epam.jwd.training.command.RequestParameter;
import com.epam.jwd.training.command.SessionAttribute;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.entity.User;
import com.epam.jwd.training.model.service.UserService;
import com.epam.jwd.training.model.service.impl.UserServiceImpl;
import com.epam.jwd.training.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UpdateUserNameAndSurnameCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(UpdateUserNameAndSurnameCommand.class);

    private final UserService userService = UserServiceImpl.getInstance();
    private final UserValidator userValidator = UserValidator.getInstance();

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        String name = request.getParameter(RequestParameter.NAME);
        String surname = request.getParameter(RequestParameter.SURNAME);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        CommandResponse response = new CommandResponse();
        boolean isCorrectData = true;
        try {
            if (!userValidator.isValidNameAndSurname(name, surname)) {
                request.setAttribute(RequestAttribute.ERROR_NAME_AND_SURNAME_MESSAGE, true);
                response.setPagePath(PagePath.PROFILE.getServletPath());
                isCorrectData = false;
            }
            if (isCorrectData) {
                userService.updateNameAndSurname(name, surname, user.getId());
                user.setName(name);
                user.setSurname(surname);
                session.setAttribute(SessionAttribute.SUCCESS, true);
                response.setType(CommandResponse.Type.REDIRECT);
                response.setPagePath(PagePath.PROFILE.getServletPath());
            }
        } catch (ServiceException e) {
            LOGGER.error(e);
            response.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return response;
    }

}
