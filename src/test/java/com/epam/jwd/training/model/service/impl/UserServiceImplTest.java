package com.epam.jwd.training.model.service.impl;

import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.entity.RoleType;
import com.epam.jwd.training.model.entity.User;
import com.epam.jwd.training.model.service.CourseService;
import com.epam.jwd.training.model.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class UserServiceImplTest {

    private final UserService userServiceTest = UserServiceImpl.getInstance();
    private final CourseService courseService = CourseServiceImpl.getInstance();

    private User expected = User.builder()
            .setName("Name")
            .setSurname("Surname")
            .setEmail("email")
            .setEnabled(true)
            .setRole(RoleType.USER)
            .build();

    @Before
    public void saveUser() throws ServiceException {
        userServiceTest.addUser(expected, "password");
    }

    @After
    public void deleteUser() throws ServiceException {
        userServiceTest.delete(expected.getId());
    }

    @Test
    public void test_findById() throws ServiceException {
        Optional<User> actualOptional = userServiceTest.findById(expected.getId());
        assertNotNull(actualOptional);
        assertEquals(Optional.of(expected), actualOptional);
    }

    @Test
    public void test_findByEmail() throws ServiceException {
        Optional<User> optionalUser = userServiceTest.findByEmail(expected.getEmail());
        assertNotNull(optionalUser);
        assertEquals(Optional.of(expected), optionalUser);
    }

    @Test
    public void test_findAll() throws ServiceException {
        List<User> actualList = userServiceTest.findAll();
        assertNotNull(actualList);
    }

    @Test
    public void test_findByEmailAndPassword() throws ServiceException {
        Optional<User> actualOptional = userServiceTest.findByEmailAndPassword(expected.getEmail(), "password");
        assertEquals(Optional.of(expected), actualOptional);
    }

    @Test
    public void test_isHaveCourse_returnFalseIfHasNotCourse() throws ServiceException {
        assertFalse(userServiceTest.isHaveCourse(expected.getId(), 1L));
    }

//    @Test
//    public void updateUserToAdmin() throws ServiceException {
//
//       userServiceTest.updateUserToAdmin(userServiceTest.findById(expected.getId()).get());
//        assertEquals(RoleType.ADMIN, userServiceTest.findById(expected.getId()).get().getRole());
//    }

}