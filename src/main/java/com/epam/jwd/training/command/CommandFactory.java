package com.epam.jwd.training.command;

import com.epam.jwd.training.command.impl.UnknownCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Command factory.
 *
 * @author Nadzeya Zmushka
 */
public class CommandFactory {

    private static final Logger LOGGER = LogManager.getLogger(CommandFactory.class);

    /**
     * Define command.
     *
     * @param request the request
     * @return {@link Command}
     */
    public static Command defineCommand(HttpServletRequest request) {
        String commandName = request.getParameter(RequestParameter.COMMAND);
        if (commandName == null || commandName.isEmpty()) {
            return new UnknownCommand();
        }
        try {
            CommandType type = CommandType.valueOf(commandName.toUpperCase());
            return type.getCommand();

        } catch (IllegalArgumentException e) {
            LOGGER.error(e);
            return new UnknownCommand();
        }
    }

}
