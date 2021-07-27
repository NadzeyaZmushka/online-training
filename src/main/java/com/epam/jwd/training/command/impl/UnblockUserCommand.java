package com.epam.jwd.training.command.impl;

import com.epam.jwd.training.command.Command;
import com.epam.jwd.training.command.CommandResponse;
import com.epam.jwd.training.command.PagePath;
import com.epam.jwd.training.command.RequestParameter;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.service.UserService;
import com.epam.jwd.training.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UnblockUserCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(UnblockUserCommand.class);

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        String userIdString = request.getParameter(RequestParameter.USER_ID);
        CommandResponse response = new CommandResponse();
        try {
            Long userId = Long.valueOf(userIdString);
            userService.changeUserStatus(userId, true);
            response.setType(CommandResponse.Type.REDIRECT);
            response.setPagePath(PagePath.SHOW_ALL_USERS.getServletPath());
        } catch (ServiceException e) {
            LOGGER.error(e);
            response.setPagePath(PagePath.ERROR_500.getDirectUrl());
            response.setType(CommandResponse.Type.REDIRECT);
        }
        return response;
    }

}