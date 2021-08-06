package com.epam.jwd.training.command.impl;

import com.epam.jwd.training.command.Command;
import com.epam.jwd.training.command.CommandResponse;
import com.epam.jwd.training.command.PagePath;
import com.epam.jwd.training.command.RequestAttribute;
import com.epam.jwd.training.command.RequestParameter;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.service.UserService;
import com.epam.jwd.training.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The command blocks user.
 *
 * @author Nadzeya Zmushka
 */
public class BlockUserCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(BlockUserCommand.class);

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        String userIdString = request.getParameter(RequestParameter.USER_ID);
//        HttpSession session = request.getSession();
        CommandResponse response = new CommandResponse();
//        boolean isBlocked = false;
        try {
            Long userId = Long.valueOf(userIdString);
            userService.changeUserStatus(userId, false);
            response.setType(CommandResponse.Type.REDIRECT);
            response.setPagePath(PagePath.SHOW_ALL_USERS.getServletPath());
        } catch (ServiceException e) {
            LOGGER.error(e);
            response.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return response;
    }

}
