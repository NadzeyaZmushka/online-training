package com.epam.jwd.training.command;

import java.util.Set;

/**
 * Enum contains page access types
 *
 * @author Nadzeya Zmushka
 */
public enum PageAccessType {

    GUEST(Set.of(
            CommandType.MAIN_PAGE,
            CommandType.COURSE_PAGE,
            CommandType.SIGN_IN_PAGE,
            CommandType.SIGN_IN,
            CommandType.SIGN_UP_PAGE,
            CommandType.SIGN_UP,
            CommandType.CHANGE_LANGUAGE,
            CommandType.PROFILE_PAGE,
            CommandType.REVIEW_PAGE,
            CommandType.ABOUT_US_PAGE,
            CommandType.CONTACTS_PAGE
    )),
    USER(Set.of(
            CommandType.MAIN_PAGE,
            CommandType.COURSE_PAGE,
            CommandType.SIGN_OUT,
            CommandType.ENROLL_COURSE,
            CommandType.UNSUBSCRIBE,
            CommandType.CHANGE_LANGUAGE,
            CommandType.PROFILE_PAGE,
            CommandType.UPDATE_USER_NAME_SURNAME,
            CommandType.UPDATE_PASSWORD,
            CommandType.REVIEW_ADD,
            CommandType.REVIEW_PAGE,
            CommandType.REVIEW_DELETE,
            CommandType.ABOUT_US_PAGE,
            CommandType.CONTACTS_PAGE
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
            CommandType.UPDATE_DESCRIPTION,
            CommandType.UPDATE_HOURS,
            CommandType.UPDATE_START_END,
            CommandType.UPDATE_TEACHER,
            CommandType.LECTURE_ADD,
            CommandType.LECTURE_UPDATE,
            CommandType.LECTURE_DELETE,
            CommandType.PROFILE_PAGE,
            CommandType.SHOW_ALL_TEACHERS,
            CommandType.UPDATE_USER_NAME_SURNAME,
            CommandType.UPDATE_PASSWORD,
            CommandType.HIDE_ALL_TEACHERS,
            CommandType.SHOW_ALL_USERS,
            CommandType.HIDE_ALL_USERS,
            CommandType.SHOW_ALL_USERS_ENROLLED_COURSE,
            CommandType.HIDE_ALL_USERS_ENROLLED_COURSE,
            CommandType.REVIEW_PAGE,
            CommandType.REVIEW_DELETE,
            CommandType.TEACHER_ADD,
            CommandType.TEACHER_DELETE,
            CommandType.UPDATE_TO_ADMIN,
            CommandType.ABOUT_US_PAGE,
            CommandType.BLOCK_USER,
            CommandType.UNBLOCK_USER,
            CommandType.CONTACTS_PAGE
    ));

    private final Set<CommandType> commands;

    PageAccessType(Set<CommandType> commands) {
        this.commands = commands;
    }

    /**
     * @return the commands {@link Set<CommandType>}
     */
    public Set<CommandType> getCommands() {
        return commands;
    }

}
