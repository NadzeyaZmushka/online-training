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
import com.epam.jwd.training.validator.UserAndTeacherValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * The command update password
 *
 * @author Nadzeya Zmushka
 */
public class UpdatePasswordCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(UpdatePasswordCommand.class);

    private final UserService userService = UserServiceImpl.getInstance();
    private final UserAndTeacherValidator userValidator = UserAndTeacherValidator.getInstance();

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        String password = new String(request.getParameter(RequestParameter.PASSWORD).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String repeatPassword = new String(request.getParameter(RequestParameter.REPEAT_PASSWORD).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        CommandResponse response = new CommandResponse();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        boolean isCorrectData = true;
        try {
            if (!userValidator.isValidPassword(password, repeatPassword)) {
                request.setAttribute(RequestAttribute.ERROR_PASSWORD_MESSAGE, true);
                response.setPagePath(PagePath.PROFILE.getServletPath());
                isCorrectData = false;
            }
            if (isCorrectData) {
                Optional<User> userOptional = userService.findByEmailAndPassword(user.getEmail(), password);
                if (userOptional.isPresent()) {
                    session.setAttribute(SessionAttribute.ERROR_CANNOT_UPDATE_PASSWORD, true);
                    response.setType(CommandResponse.Type.REDIRECT);
                    response.setPagePath(PagePath.PROFILE.getServletPath());
                } else {
                    userService.updatePassword(password, user);
                    session.setAttribute(SessionAttribute.SUCCESS, true);
                    response.setType(CommandResponse.Type.REDIRECT);
                    response.setPagePath(PagePath.PROFILE.getServletPath());
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
