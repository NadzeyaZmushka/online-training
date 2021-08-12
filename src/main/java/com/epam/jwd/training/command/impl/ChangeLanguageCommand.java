package com.epam.jwd.training.command.impl;

import com.epam.jwd.training.command.Command;
import com.epam.jwd.training.command.CommandResponse;
import com.epam.jwd.training.command.RequestAttribute;
import com.epam.jwd.training.command.RequestParameter;
import com.epam.jwd.training.command.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The command change language
 *
 * @author Nadzeya Zmushka
 */
public class ChangeLanguageCommand implements Command {

    private static final String EN = "en";
    private static final String RU = "ru";

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        String language = request.getParameter(RequestParameter.LANGUAGE);

        HttpSession session = request.getSession();
        String pagePath = (String) session.getAttribute(SessionAttribute.CURRENT_PAGE);
        if (EN.equals(language)) {
            session.setAttribute(RequestAttribute.LANGUAGE, EN);
        } else {
            session.setAttribute(RequestAttribute.LANGUAGE, RU);
        }
        CommandResponse response = new CommandResponse();
        response.setPagePath(pagePath);
//        response.setType(CommandResponse.Type.REDIRECT);
        return response;
    }
}
