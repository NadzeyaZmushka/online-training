package com.epam.jwd.training.command.impl;

import com.epam.jwd.training.command.Command;
import com.epam.jwd.training.command.CommandResponse;
import com.epam.jwd.training.command.PagePath;
import com.epam.jwd.training.command.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The command hides all users on courses
 *
 * @author Nadzeya Zmushka
 */
public class HideUsersEnrolledCourseCommand implements Command {
    @Override
    public CommandResponse execute(HttpServletRequest request) {
        CommandResponse response = new CommandResponse();
        HttpSession session = request.getSession();
        session.removeAttribute(SessionAttribute.USERS_ENROLLED_COURSE);
        response.setType(CommandResponse.Type.REDIRECT);
        response.setPagePath(PagePath.PROFILE.getServletPath());
        return response;
    }
}
