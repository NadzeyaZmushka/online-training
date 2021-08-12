package com.epam.jwd.training.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CourseValidator {

    private static final CourseValidator INSTANCE = new CourseValidator();

    private static final Pattern HOURS_VALIDATOR = Pattern.compile("\\d+");
    private static final Pattern COST_VALIDATOR = Pattern.compile("\\d+[.]\\d{2,8}");
    private static final Pattern STRING_PATTERN = Pattern.compile("^[\\p{L}]+$");

    private CourseValidator() {
    }

    public boolean isValidName(String courseName) {
        boolean isCorrect = true;
        if (courseName == null || courseName.isBlank()) {
            isCorrect = false;
        }
        return isCorrect;
    }

    public boolean isValidDescription(String description) {
        boolean isCorrect = true;
        if (description == null || description.isBlank()) {
            isCorrect = false;
        }
        return isCorrect;
    }

    public boolean isValidLecture(String message) {
        boolean isCorrect = true;
        if (message == null || message.isBlank()) {
            isCorrect = false;
        }
        return isCorrect;
    }

    public boolean isValidHours(String hours) {
        if (hours == null || hours.isBlank()) {
            return false;
        }
        Matcher matcher = HOURS_VALIDATOR.matcher(hours);
        return matcher.matches();
    }

    public boolean isValidNameAndSurname(String name, String surname) {
        boolean isCorrect = true;
        if (name == null || name.isBlank() || surname == null || surname.isBlank()
                || !STRING_PATTERN.matcher(name).matches() || !STRING_PATTERN.matcher(surname).matches()) {
            isCorrect = false;
        }
        return isCorrect;
    }

    public boolean isValidCost(String cost) {
        if (cost == null || cost.isBlank()) {
            return false;
        }
        Matcher matcher = COST_VALIDATOR.matcher(cost);
        return matcher.matches();
    }

    public boolean isValidDate(String startCourse, String endCourse) {
        boolean isCorrect = true;
        if (startCourse == null || startCourse.isBlank() || endCourse == null || endCourse.isBlank()) {
            isCorrect = false;
        }
        return isCorrect;
    }

    public static CourseValidator getInstance() {
        return INSTANCE;
    }

}
