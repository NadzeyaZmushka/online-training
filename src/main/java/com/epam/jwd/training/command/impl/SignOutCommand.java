package com.epam.jwd.training.command.impl;

import com.epam.jwd.training.command.Command;
import com.epam.jwd.training.command.CommandResponse;
import com.epam.jwd.training.command.PagePath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SignOutCommand implements Command {

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        CommandResponse response = new CommandResponse();
        HttpSession session = request.getSession();
        session.invalidate();
        response.setPagePath(PagePath.INDEX.getDirectUrl());
        response.setType(CommandResponse.Type.REDIRECT);
        return response;
    }

}
