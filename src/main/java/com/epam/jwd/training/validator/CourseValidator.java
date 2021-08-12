package com.epam.jwd.training.validator;

/**
 * Class validates course details
 *
 * @author Nadzeya Zmushka
 */
public class CourseValidator {

    private static final CourseValidator INSTANCE = new CourseValidator();

    private static final String HOURS_REGEX = "\\d+";
    private static final String COST_REGEX = "\\d+[.]\\d{2,3}";
    private static final String STRING_REGEX = "[.[^<>]]{1,1000}";

    private CourseValidator() {
    }

    public boolean isValidName(String courseName) {
        if (courseName == null || courseName.isBlank()) {
            return false;
        }
        return courseName.matches(STRING_REGEX);
    }

    public boolean isValidDescription(String description) {
        if (description == null || description.isBlank()) {
            return false;
        }
        return description.matches(STRING_REGEX);
    }

    public boolean isValidLecture(String lecture) {
        if (lecture == null || lecture.isBlank()) {
            return false;
        }
        return lecture.matches(STRING_REGEX);
    }

    public boolean isValidHours(String hours) {
        if (hours == null || hours.isBlank()) {
            return false;
        }
        return hours.matches(HOURS_REGEX);
    }

    public boolean isValidCost(String cost) {
        if (cost == null || cost.isBlank()) {
            return false;
        }
        return cost.matches(COST_REGEX);
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
