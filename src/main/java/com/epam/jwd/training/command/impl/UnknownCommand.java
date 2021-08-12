package com.epam.jwd.training.command.impl;

import com.epam.jwd.training.command.Command;
import com.epam.jwd.training.command.CommandResponse;
import com.epam.jwd.training.command.PagePath;

import javax.servlet.http.HttpServletRequest;

/**
 * This type of command returns when system can't define the command
 *
 * @author Nadzeya Zmushka
 */
public class UnknownCommand implements Command {
    @Override
    public CommandResponse execute(HttpServletRequest request) {
        CommandResponse response = new CommandResponse();
        response.setPagePath(PagePath.ERROR_404.getDirectUrl());
        return response;
    }
}
