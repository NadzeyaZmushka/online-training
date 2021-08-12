package com.epam.jwd.training.validator;

/**
 * Class validates user name, surname, email, password
 * and teacher name and surname
 *
 * @author Nadzeya Zmushka
 */
public class UserAndTeacherValidator {

    private static final UserAndTeacherValidator INSTANCE = new UserAndTeacherValidator();

    private static final String EMAIL_REGEX = "^.+@.+\\..{2,4}$";
    //??
    private static final String PASSWORD_REGEX = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$";

    private final static String NAME_REGEX = "^[A-za-z-]{3,16}$";

    private UserAndTeacherValidator() {
    }

    public boolean isValidNameAndSurname(String name, String surname) {
        boolean isCorrect = true;
        if (name == null || name.isBlank() || surname == null || surname.isBlank()
                || !name.matches(NAME_REGEX) || !surname.matches(NAME_REGEX)) {
            isCorrect = false;
        }
        return isCorrect;
    }

    public boolean isValidEmail(String email) {
        if (email == null || email.isBlank()) {
            return false;
        }
        return email.matches(EMAIL_REGEX);
    }

    public boolean isValidPassword(String password, String repeatPassword) {
        if (password == null || password.isBlank()
                || repeatPassword == null || repeatPassword.isBlank()
                || !password.equals(repeatPassword)) {
            return false;
        }
        return password.matches(PASSWORD_REGEX);
    }

    public boolean isValidEmailAndPassword(String email, String password) {
        boolean isCorrect = true;
        if (email == null || email.isBlank() || password == null || password.isBlank()) {
            isCorrect = false;
        }
        return isCorrect;
    }

    public static UserAndTeacherValidator getInstance() {
        return INSTANCE;
    }

}
