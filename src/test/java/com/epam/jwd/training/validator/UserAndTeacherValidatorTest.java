package com.epam.jwd.training.validator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserAndTeacherValidatorTest {

    private UserAndTeacherValidator validator;
    private String name;
    private String surname;
    private String email;
    private String password;

    @Before
    public void setUp() {
        validator = UserAndTeacherValidator.getInstance();
        name = "Name";
        surname = "Surname";
        email = "test@mail.ru";
        password = "Aab12345@";

    }

    @Test
    public void test_isValidNameAndSurname_returnTrue_whenEmailIsValid() {
        boolean actual = validator.isValidEmail(email);

        assertTrue(actual);
    }

    @Test
    public void test_IsValidNameAndSurname_returnTrue_whenNameSurnameAreValid() {
        boolean actual = validator.isValidNameAndSurname(name, surname);

        assertTrue(actual);
    }

    @Test
    public void test_isValidPassword_returnTrue_whenPasswordIsValid() {
        boolean actual = validator.isValidPassword(password, "Aab12345@");

        assertTrue(actual);
    }

    /**
     * Test
     * Return false when password and repeat password are not the same
     */
    @Test
    public void test_isValidPassword_returnFalse() {
        boolean actual = validator.isValidPassword(password, "Aab1295885#");

        assertFalse(actual);
    }

    /**
     * Test
     * Return false whe email and password are "null"
     */
    @Test
    public void test_isValidEmailAndPassword_returnFalse() {
        boolean actual = validator.isValidEmailAndPassword(null, null);

        assertFalse(actual);
    }

}