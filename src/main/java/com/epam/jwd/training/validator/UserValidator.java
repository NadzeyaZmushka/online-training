package com.epam.jwd.training.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    private static final UserValidator INSTANCE = new UserValidator();

    private static final String EMAIL_REGEX = "\\w+@\\p{Alpha}+\\.\\p{Alpha}{2,}";
    //??
    private static final String PASSWORD_REGEX = "[a-zA-Z\\d]{1,15}";
    //??
    private final static String NAME_REGEX = "^[\\p{L}]+$";

    private UserValidator() {
    }

    public boolean isValidNameAndSurname(String name, String surname) {
        boolean isCorrect = true;
        Pattern pattern = Pattern.compile(NAME_REGEX);
        if (name == null || name.isBlank() || surname == null || surname.isBlank()
                || !pattern.matcher(name).matches() || !pattern.matcher(surname).matches()) {
            isCorrect = false;
        }
        return isCorrect;
    }

    public boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        if (email == null || email.isBlank()) {
            return false;
        }
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isValidPassword(String password, String repeatPassword) {
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        if (password == null || password.isBlank()
                || repeatPassword == null || repeatPassword.isBlank()
                || !password.equals(repeatPassword)) {
            return false;
        }
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public boolean isValidEmailAndPassword(String email, String password) {
        boolean isCorrect = true;
        if (email == null || email.isBlank() || password == null || password.isBlank()) {
            isCorrect = false;
        }
        return isCorrect;
    }

    public static UserValidator getInstance() {
        return INSTANCE;
    }

}
