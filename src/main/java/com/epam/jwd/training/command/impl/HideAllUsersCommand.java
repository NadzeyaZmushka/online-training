package com.epam.jwd.training.command.impl;

import com.epam.jwd.training.command.Command;
import com.epam.jwd.training.command.CommandResponse;
import com.epam.jwd.training.command.PagePath;
import com.epam.jwd.training.command.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class HideAllUsersCommand implements Command {

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        CommandResponse response = new CommandResponse();
        HttpSession session = request.getSession();
        session.removeAttribute(SessionAttribute.ALL_USERS);
        response.setType(CommandResponse.Type.REDIRECT);
        response.setPagePath(PagePath.PROFILE.getServletPath());
        return response;
    }

}
