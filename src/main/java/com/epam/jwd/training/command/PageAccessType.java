package com.epam.jwd.training.command;

import java.util.Set;

public enum PageAccessType {

    GUEST(Set.of(
            CommandType.MAIN_PAGE,
            CommandType.COURSE_PAGE,
            CommandType.SIGN_IN_PAGE,
            CommandType.SIGN_IN,
            CommandType.SIGN_UP_PAGE,
            CommandType.SIGN_UP,
            CommandType.CHANGE_LANGUAGE
    )),
    USER(Set.of(
            CommandType.MAIN_PAGE,
            CommandType.COURSE_PAGE,
            CommandType.SIGN_OUT,
            CommandType.CHANGE_LANGUAGE
    )),
    ADMIN(Set.of(
            CommandType.MAIN_PAGE,
            CommandType.COURSE_PAGE,
            CommandType.SIGN_OUT,
            CommandType.CHANGE_LANGUAGE,
            CommandType.COURSE_DELETE,
            CommandType.COURSE_ADD,
            CommandType.UPDATE_COST,
            CommandType.COURSE_UPDATE,
            CommandType.DESCRIPTION_UPDATE
    ));

    private final Set<CommandType> commands;

    PageAccessType(Set<CommandType> commands) {
        this.commands = commands;
    }

    public Set<CommandType> getCommands() {
        return commands;
    }
}
