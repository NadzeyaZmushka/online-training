package com.epam.jwd.training.model.service.impl;

import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.entity.RoleType;
import com.epam.jwd.training.model.entity.User;
import com.epam.jwd.training.model.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class UserServiceImplTest {

    private final UserService userServiceTest = UserServiceImpl.getInstance();

    private final User expected = User.builder()
            .setName("Name")
            .setSurname("Surname")
            .setEmail("email")
            .setEnabled(true)
            .setRole(RoleType.USER)
            .build();
    private final String password = "password";

    @Before
    public void saveUser() throws ServiceException {
        userServiceTest.addUser(expected, password);
    }

    @After
    public void deleteUser() throws ServiceException {
        userServiceTest.delete(expected.getId());
    }

    @Test
    public void test_delete_mustDeleteUser() throws ServiceException {
        userServiceTest.delete(expected.getId());
        List<User> allUsers = userServiceTest.findAll();

        assertFalse(allUsers.contains(expected));
    }

    @Test
    public void test_findById_returnUserWithSuchId() throws ServiceException {
        Optional<User> actualOptional = userServiceTest.findById(expected.getId());

        assertEquals(Optional.of(expected), actualOptional);
    }


    @Test
    public void test_findById_returnOptionalEmpty_ifUserWasNotFound() throws ServiceException {
        Optional<User> user = userServiceTest.findById(0L);

        assertTrue(user.isEmpty());
    }

    @Test
    public void test_findByEmail_returnUserWithThisEmail() throws ServiceException {
        Optional<User> optionalUser = userServiceTest.findByEmail(expected.getEmail());

        assertNotNull(optionalUser);
        assertEquals(Optional.of(expected), optionalUser);
    }

    @Test
    public void test_findByEmail_returnOptionalEmpty_ifSuchEmailIsNotExist() throws ServiceException {
        String email = "qwerty";
        Optional<User> userOptional = userServiceTest.findByEmail(email);

        assertTrue(userOptional.isEmpty());
    }

    @Test
    public void test_findAll() throws ServiceException {
        List<User> actualList = userServiceTest.findAll();

        assertNotNull(actualList);
    }

    @Test
    public void test_findByEmailAndPassword_returnOptionalEmpty_ifSuchEmailOrPasswordAreNotExist() throws ServiceException {
        String actualPassword = "actualPassword";
        Optional<User> userOptional = userServiceTest.findByEmailAndPassword(expected.getEmail(), actualPassword);

        assertTrue(userOptional.isEmpty());
    }

    @Test
    public void test_findByEmailAndPassword_returnUserWithSuchEmailPassword() throws ServiceException {
        Optional<User> actualOptional = userServiceTest.findByEmailAndPassword(expected.getEmail(), password);
        assertEquals(Optional.of(expected), actualOptional);
    }

    @Test
    public void test_isHaveCourse_returnFalseIfHasNotCourse() throws ServiceException {
        assertFalse(userServiceTest.isHaveCourse(expected.getId(), 1L));
    }

    @Test
    public void test_updateNameAndSurname() throws ServiceException {

        String newName = "Aaa";
        String newSurname = "Bbb";
        userServiceTest.updateNameAndSurname(newName, newSurname, expected.getId());
        User newUser = userServiceTest.findById(expected.getId()).get();
        String actualName = newUser.getName();
        String actualSurname = newUser.getSurname();

        assertEquals(newName, actualName);
        assertEquals(newSurname, actualSurname);
    }

    @Test
    public void test_updateUserToAdmin() throws ServiceException {
        RoleType roleType = expected.getRole();
        userServiceTest.updateUserToAdmin(expected);
        User actual = userServiceTest.findById(expected.getId()).get();
        RoleType roleType1 = actual.getRole();

        assertEquals(roleType1, roleType);
    }

    // ???
    @Test
    public void test_updatePassword() throws ServiceException {
        User actual = userServiceTest.findById(expected.getId()).get();
        String newPassword = "newPassword";

        assertTrue(userServiceTest.updatePassword(newPassword, actual));
    }
}