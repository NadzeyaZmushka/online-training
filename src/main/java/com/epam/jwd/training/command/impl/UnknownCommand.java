package com.epam.jwd.training.command.impl;

import com.epam.jwd.training.command.Command;
import com.epam.jwd.training.command.CommandResponse;
import com.epam.jwd.training.command.PagePath;

import javax.servlet.http.HttpServletRequest;

public class UnknownCommand implements Command {
    @Override
    public CommandResponse execute(HttpServletRequest request) {
        CommandResponse response = new CommandResponse();
        response.setPagePath(PagePath.INDEX.getDirectUrl());
        return response;
    }
}
