package com.epam.jwd.training.command.impl;

import com.epam.jwd.training.command.Command;
import com.epam.jwd.training.command.CommandResponse;
import com.epam.jwd.training.command.PagePath;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.dao.impl.UserDaoImpl;
import com.epam.jwd.training.model.entity.RoleType;
import com.epam.jwd.training.model.entity.User;
import com.epam.jwd.training.model.service.UserService;
import com.epam.jwd.training.model.service.impl.UserServiceImpl;
import com.epam.jwd.training.validator.UserAndTeacherValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static com.epam.jwd.training.command.RequestAttribute.ERROR_EMAIL_MESSAGE_INVALID;
import static com.epam.jwd.training.command.RequestAttribute.ERROR_EMAIL_MESSAGE_IS_EXIST;
import static com.epam.jwd.training.command.RequestAttribute.ERROR_NAME_AND_SURNAME_MESSAGE;
import static com.epam.jwd.training.command.RequestAttribute.ERROR_PASSWORD_MESSAGE;
import static com.epam.jwd.training.command.RequestAttribute.EXCEPTION;
import static com.epam.jwd.training.command.RequestParameter.EMAIL;
import static com.epam.jwd.training.command.RequestParameter.NAME;
import static com.epam.jwd.training.command.RequestParameter.PASSWORD;
import static com.epam.jwd.training.command.RequestParameter.REPEAT_PASSWORD;
import static com.epam.jwd.training.command.RequestParameter.SURNAME;

/**
 * Sign out command
 *
 * @author Nadzeya Zmushka
 */
public class SignUpCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(SignUpCommand.class);

    private final UserService userService = new UserServiceImpl(new UserDaoImpl());
    private final UserAndTeacherValidator userValidator = UserAndTeacherValidator.getInstance();

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        String email = request.getParameter(EMAIL);
        String name = new String(request.getParameter(NAME).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String surname = new String(request.getParameter(SURNAME).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String password = new String(request.getParameter(PASSWORD).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String repeatPassword = new String(request.getParameter(REPEAT_PASSWORD).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        CommandResponse response = new CommandResponse();
        boolean isCorrectData = true;
        try {
            if (!userValidator.isValidEmail(email)) {
                request.setAttribute(ERROR_EMAIL_MESSAGE_INVALID, true);
                isCorrectData = false;
            }
            if (!userValidator.isValidNameAndSurname(name, surname)) {
                request.setAttribute(ERROR_NAME_AND_SURNAME_MESSAGE, true);
                isCorrectData = false;
            }
            if (!userValidator.isValidPassword(password, repeatPassword)) {
                request.setAttribute(ERROR_PASSWORD_MESSAGE, true);
                isCorrectData = false;
            }
            if (isCorrectData) {
                Optional<User> userOptional = userService.findByEmail(email);
                if (userOptional.isPresent()) {
                    request.setAttribute(ERROR_EMAIL_MESSAGE_IS_EXIST, true);
                    isCorrectData = false;
                }
            }
            if (isCorrectData) {
                User user = new User(name, surname, email, RoleType.USER, true);
                userService.addUser(user, password);
                response.setPagePath(PagePath.SIGN_IN.getServletPath());
                response.setType(CommandResponse.Type.REDIRECT);
            } else {
                response.setPagePath(PagePath.SIGN_UP.getDirectUrl());
                request.setAttribute(EMAIL, email);
                request.setAttribute(NAME, name);
                request.setAttribute(SURNAME, surname);
            }
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            response.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(EXCEPTION, e.getMessage());
        }
        return response;
    }

}
