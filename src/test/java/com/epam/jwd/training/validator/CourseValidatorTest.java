package com.epam.jwd.training.validator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CourseValidatorTest {

    private CourseValidator validator;
    String name;
    String description;
    String lecture;
    String hours;
    String cost;

    @Before
    public void setUp() throws Exception {
        validator = CourseValidator.getInstance();
        name = "Course";
        description = "Course description!";
        lecture = "First lecture";
        hours = "77";
        cost = "123.45";
    }

    @Test
    public void test_isValidName_returnTrue_whenNameIsValid() {
        boolean actual = validator.isValidName(name);

        assertTrue(actual);
    }
    @Test
    public void test_isValidName_returnFalse_whenNameIsNotValid() {
        String wrongName = "<>name</>";
        boolean actual = validator.isValidName(wrongName);

        assertFalse(actual);
    }
    @Test
    public void test_isValidDescription_returnTrue_whenDescriptionIsValid() {
        boolean actual = validator.isValidDescription(description);

        assertTrue(actual);
    }

    @Test
    public void test_isValidDescription_returnFalse_whenDescriptionIsNotValid() {
        String wrongDescription = "<>description</>";
        boolean actual = validator.isValidDescription(wrongDescription);

        assertFalse(actual);
    }

    @Test
    public void test_isValidLecture_returnTrue_whenLectureIsValid() {
        boolean actual = validator.isValidLecture(lecture);

        assertTrue(actual);
    }

    @Test
    public void test_isValidLecture_returnFalse_whenLectureIsNotValid() {
        String wrongLecture = "<>lecture</>";
        boolean actual = validator.isValidLecture(wrongLecture);

        assertFalse(actual);
    }

    @Test
    public void test_isValidHours_returnTrue_whenHoursAreValid() {
        boolean actual = validator.isValidHours(hours);

        assertTrue(actual);
    }

    @Test
    public void test_isValidHours_returnFalse_whenHoursAreNotValid() {
        String wrongHours = "hour";
        boolean actual = validator.isValidHours(wrongHours);

        assertFalse(actual);
    }

    @Test
    public void test_isValidCost_returnTrue_whenCostIsValid() {
        boolean actual = validator.isValidCost(cost);

        assertTrue(actual);
    }

    @Test
    public void test_isValidCost_returnFalse_whenCostIsNotValid() {
        String wrongCost = "12345";
        boolean actual = validator.isValidCost(wrongCost);

        assertFalse(actual);
    }

    /**
     * Test
     * Return false when dates are null
     */
    @Test
    public void test_isValidDate_returnFalse() {
        boolean actual = validator.isValidDate(null, null);

        assertFalse(actual);
    }
}