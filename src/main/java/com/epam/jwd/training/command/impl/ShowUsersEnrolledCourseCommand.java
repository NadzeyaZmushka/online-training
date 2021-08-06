package com.epam.jwd.training.command.impl;

import com.epam.jwd.training.command.Command;
import com.epam.jwd.training.command.CommandResponse;
import com.epam.jwd.training.command.PagePath;
import com.epam.jwd.training.command.RequestAttribute;
import com.epam.jwd.training.command.SessionAttribute;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.entity.User;
import com.epam.jwd.training.model.service.UserService;
import com.epam.jwd.training.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The command shows all users enrolled on courses
 *
 * @author Nadzeya Zmushka
 */
public class ShowUsersEnrolledCourseCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ShowUsersEnrolledCourseCommand.class);

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        CommandResponse response = new CommandResponse();
        try {
            List<User> usersOnCourse = userService.findAllUsersOnCourse();
            session.setAttribute(SessionAttribute.USERS_ENROLLED_COURSE, usersOnCourse);
            response.setType(CommandResponse.Type.REDIRECT);
            response.setPagePath(PagePath.PROFILE.getServletPath());
        } catch (ServiceException e) {
            LOGGER.error(e);
            response.setPagePath(PagePath.ERROR_500.getServletPath());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return response;
    }
}
