package com.epam.jwd.training.command;

import javax.servlet.http.HttpServletRequest;

/**
 * The interface for different Commands.
 *
 * @author Nadzeya Zmushka
 */
public interface Command {
    /**
     * Executes command
     *
     * @param request the request {@link HttpServletRequest}
     * @return {@link CommandResponse}
     */

    CommandResponse execute(HttpServletRequest request);

}
