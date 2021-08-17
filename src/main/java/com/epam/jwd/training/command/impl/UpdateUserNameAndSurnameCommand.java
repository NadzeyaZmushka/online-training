package com.epam.jwd.training.command.impl;

import com.epam.jwd.training.command.Command;
import com.epam.jwd.training.command.CommandResponse;
import com.epam.jwd.training.command.PagePath;
import com.epam.jwd.training.command.RequestAttribute;
import com.epam.jwd.training.command.SessionAttribute;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.dao.impl.UserDaoImpl;
import com.epam.jwd.training.model.entity.User;
import com.epam.jwd.training.model.service.UserService;
import com.epam.jwd.training.model.service.impl.UserServiceImpl;
import com.epam.jwd.training.validator.UserAndTeacherValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;

import static com.epam.jwd.training.command.RequestParameter.NAME;
import static com.epam.jwd.training.command.RequestParameter.SURNAME;

/**
 * The command update name and surname
 *
 * @author Nadzeya Zmushka
 */
public class UpdateUserNameAndSurnameCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(UpdateUserNameAndSurnameCommand.class);

    private final UserService userService = new UserServiceImpl(new UserDaoImpl());
    private final UserAndTeacherValidator userValidator = UserAndTeacherValidator.getInstance();

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        String name = new String(request.getParameter(NAME).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String surname = new String(request.getParameter(SURNAME).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
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
