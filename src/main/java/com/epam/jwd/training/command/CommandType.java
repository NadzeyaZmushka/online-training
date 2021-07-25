package com.epam.jwd.training.command;

import com.epam.jwd.training.command.impl.ChangeLanguageCommand;
import com.epam.jwd.training.command.impl.CourseAddCommand;
import com.epam.jwd.training.command.impl.CourseCommand;
import com.epam.jwd.training.command.impl.CourseCostUpdateCommand;
import com.epam.jwd.training.command.impl.CourseDateUpdateCommand;
import com.epam.jwd.training.command.impl.CourseDeleteCommand;
import com.epam.jwd.training.command.impl.CourseDescriptionUpdateCommand;
import com.epam.jwd.training.command.impl.CourseHoursUpdateCommand;
import com.epam.jwd.training.command.impl.CourseTeacherUpdateCommand;
import com.epam.jwd.training.command.impl.CourseUpdateCommand;
import com.epam.jwd.training.command.impl.EnrollOnCourseCommand;
import com.epam.jwd.training.command.impl.HideAllTeachersCommand;
import com.epam.jwd.training.command.impl.HideAllUsersCommand;
import com.epam.jwd.training.command.impl.HideUsersEnrolledCourseCommand;
import com.epam.jwd.training.command.impl.LectureAddCommand;
import com.epam.jwd.training.command.impl.LectureDeleteCommand;
import com.epam.jwd.training.command.impl.LectureUpdateCommand;
import com.epam.jwd.training.command.impl.MainCommand;
import com.epam.jwd.training.command.impl.ProfileCommand;
import com.epam.jwd.training.command.impl.ShowAllTeachersCommand;
import com.epam.jwd.training.command.impl.ShowAllUsersCommand;
import com.epam.jwd.training.command.impl.ShowUsersEnrolledCourseCommand;
import com.epam.jwd.training.command.impl.SignInCommand;
import com.epam.jwd.training.command.impl.SignOutCommand;
import com.epam.jwd.training.command.impl.SignUpCommand;
import com.epam.jwd.training.command.impl.ToPageCommand;
import com.epam.jwd.training.command.impl.UpdatePasswordCommand;
import com.epam.jwd.training.command.impl.UpdateUserNameAndSurnameCommand;

public enum CommandType {

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
    UPDATE_HOURS(new CourseHoursUpdateCommand()),
    UPDATE_START_END(new CourseDateUpdateCommand()),
    COURSE_UPDATE(new CourseUpdateCommand()),
    UPDATE_DESCRIPTION(new CourseDescriptionUpdateCommand()),
    UPDATE_TEACHER(new CourseTeacherUpdateCommand()),
    LECTURE_ADD(new LectureAddCommand()),
    LECTURE_UPDATE(new LectureUpdateCommand()),
    LECTURE_DELETE(new LectureDeleteCommand()),
    ENROLL_COURSE(new EnrollOnCourseCommand()),
    PROFILE_PAGE(new ProfileCommand()),
    UPDATE_USER_NAME_SURNAME(new UpdateUserNameAndSurnameCommand()),
    UPDATE_PASSWORD(new UpdatePasswordCommand()),
    SHOW_ALL_TEACHERS(new ShowAllTeachersCommand()),
    HIDE_ALL_TEACHERS(new HideAllTeachersCommand()),
    SHOW_ALL_USERS(new ShowAllUsersCommand()),
    HIDE_ALL_USERS(new HideAllUsersCommand()),
    SHOW_ALL_USERS_ENROLLED_COURSE(new ShowUsersEnrolledCourseCommand()),
    HIDE_ALL_USERS_ENROLLED_COURSE(new HideUsersEnrolledCourseCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

}
