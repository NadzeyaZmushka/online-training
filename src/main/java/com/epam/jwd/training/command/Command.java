package com.epam.jwd.training.command;

import javax.servlet.http.HttpServletRequest;

public interface Command {

    CommandResponse execute(HttpServletRequest request);

}
