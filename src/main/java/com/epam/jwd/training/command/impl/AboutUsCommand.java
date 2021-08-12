package com.epam.jwd.training.command.impl;

import com.epam.jwd.training.command.Command;
import com.epam.jwd.training.command.CommandResponse;
import com.epam.jwd.training.command.PagePath;
import com.epam.jwd.training.command.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The command shows info about us.
 *
 * @author Nadzeya Zmushka
 */
public class AboutUsCommand implements Command {

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        CommandResponse response = new CommandResponse();
        HttpSession session = request.getSession();
        response.setPagePath(PagePath.ABOUT_US.getDirectUrl());
        session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.ABOUT_US.getServletPath());
        return response;
    }

}
