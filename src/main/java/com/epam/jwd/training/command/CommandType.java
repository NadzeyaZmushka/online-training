package com.epam.jwd.training.command;

import com.epam.jwd.training.command.impl.CourseAddCommand;
import com.epam.jwd.training.command.impl.CourseCostUpdateCommand;
import com.epam.jwd.training.command.impl.CourseDeleteCommand;
import com.epam.jwd.training.command.impl.CourseCommand;
import com.epam.jwd.training.command.impl.CourseDescriptionUpdateCommand;
import com.epam.jwd.training.command.impl.CourseUpdateCommand;
import com.epam.jwd.training.command.impl.MainCommand;
import com.epam.jwd.training.command.impl.ChangeLanguageCommand;
import com.epam.jwd.training.command.impl.SignInCommand;
import com.epam.jwd.training.command.impl.SignOutCommand;
import com.epam.jwd.training.command.impl.SignUpCommand;
import com.epam.jwd.training.command.impl.ToPageCommand;

public enum  CommandType {

    MAIN_PAGE(new MainCommand()),
    SIGN_IN(new SignInCommand()),
    SIGN_UP(new SignUpCommand()),
   COURSE_PAGE(new CourseCommand()),
    CHANGE_LANGUAGE(new ChangeLanguageCommand()),
    SIGN_IN_PAGE(new ToPageCommand(PagePath.SIGN_IN.getDirectUrl())),
    SIGN_UP_PAGE(new ToPageCommand(PagePath.SIGN_UP.getDirectUrl())),
    SIGN_OUT(new SignOutCommand()),
    COURSE_DELETE(new CourseDeleteCommand()),
    COURSE_ADD(new CourseAddCommand()),
    UPDATE_COST(new CourseCostUpdateCommand()),
    COURSE_UPDATE(new CourseUpdateCommand()),
    DESCRIPTION_UPDATE(new CourseDescriptionUpdateCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
