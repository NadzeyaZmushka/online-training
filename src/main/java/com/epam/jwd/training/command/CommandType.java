package com.epam.jwd.training.command;

import com.epam.jwd.training.command.impl.LectureCommand;
import com.epam.jwd.training.command.impl.MainCommand;
import com.epam.jwd.training.command.impl.ChangeLanguageCommand;
import com.epam.jwd.training.command.impl.SignInCommand;
import com.epam.jwd.training.command.impl.SignUpCommand;
import com.epam.jwd.training.command.impl.ToPageCommand;
import com.epam.jwd.training.model.service.impl.UserServiceImpl;

public enum  CommandType {

    MAIN_PAGE(new MainCommand()),
    SIGN_IN(new SignInCommand()),
    SIGN_UP(new SignUpCommand()),
    LECTURE_PAGE(new LectureCommand()),
    CHANGE_LANGUAGE(new ChangeLanguageCommand()),
    SIGN_IN_PAGE(new ToPageCommand(PagePath.SIGN_IN.getDirectUrl())),
    SIGN_UP_PAGE(new ToPageCommand(PagePath.SIGN_UP.getDirectUrl()));

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
