package com.epam.jwd.training.command.impl;

import com.epam.jwd.training.command.Command;
import com.epam.jwd.training.command.CommandResponse;
import com.epam.jwd.training.command.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ToPageCommand implements Command {

    private final String page;

    public ToPageCommand(String page) {
        this.page = page;
    }

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.CURRENT_PAGE, page);
        CommandResponse response = new CommandResponse();
        response.setPagePath(page);
        return response;
    }

}
