package com.epam.jwd.training.command;

import java.util.Set;

public enum PageAccessType {

    GUEST(Set.of(
            CommandType.MAIN_PAGE,
            CommandType.LECTURE_PAGE,
            CommandType.SIGN_IN_PAGE,
            CommandType.SIGN_IN,
            CommandType.SIGN_UP_PAGE,
            CommandType.SIGN_UP,
            CommandType.CHANGE_LANGUAGE
    )),
    USER(Set.of(
            CommandType.MAIN_PAGE,
            CommandType.LECTURE_PAGE,
            CommandType.CHANGE_LANGUAGE
    )),
    ADMIN(Set.of(
            CommandType.MAIN_PAGE,
            CommandType.LECTURE_PAGE,
            CommandType.CHANGE_LANGUAGE
    ));

    private final Set<CommandType> commands;

    PageAccessType(Set<CommandType> commands) {
        this.commands = commands;
    }

    public Set<CommandType> getCommands() {
        return commands;
    }
}
