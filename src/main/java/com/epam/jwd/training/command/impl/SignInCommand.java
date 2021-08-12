package com.epam.jwd.training.command.impl;

import com.epam.jwd.training.command.Command;
import com.epam.jwd.training.command.CommandResponse;
import com.epam.jwd.training.command.PagePath;
import com.epam.jwd.training.command.RequestAttribute;
import com.epam.jwd.training.command.RequestParameter;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.entity.User;
import com.epam.jwd.training.model.service.UserService;
import com.epam.jwd.training.model.service.impl.UserServiceImpl;
import com.epam.jwd.training.validator.UserAndTeacherValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * Sign in command
 *
 * @author Nadzeya Zmushka
 */
public class SignInCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(SignInCommand.class);

    private final UserAndTeacherValidator userValidator = UserAndTeacherValidator.getInstance();
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);
        HttpSession session = request.getSession();
        CommandResponse response = new CommandResponse();
        boolean isCorrectData = true;
        try {
            if (!userValidator.isValidEmailAndPassword(email, password)) {
                request.setAttribute(RequestAttribute.ERROR_USER_MESSAGE_IS_VALID, true);
                response.setPagePath(PagePath.SIGN_IN.getDirectUrl());
                request.setAttribute(RequestAttribute.EMAIL, email);
                isCorrectData = false;
            }
            if (isCorrectData) {
                Optional<User> user = userService.findByEmailAndPassword(email, password);
                if (user.isPresent()) {
                    if (user.get().isEnabled()) {
                        response.setPagePath(PagePath.MAIN.getServletPath());
                        response.setType(CommandResponse.Type.REDIRECT);
                        session.setAttribute(RequestAttribute.USER, user.get());
                    } else {
                        request.setAttribute(RequestAttribute.ERROR_USER_BLOCK, true);
                        request.setAttribute(RequestAttribute.EMAIL, email);
                        response.setPagePath(PagePath.SIGN_IN.getDirectUrl());
                    }
                } else {
                    request.setAttribute(RequestAttribute.ERROR_USER_MESSAGE_IS_INCORRECT, true);
                    request.setAttribute(RequestAttribute.EMAIL, email);
                    response.setPagePath(PagePath.SIGN_IN.getDirectUrl());
                }
            }
        } catch (ServiceException e) {
            LOGGER.error(e);
            response.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return response;
    }
}
