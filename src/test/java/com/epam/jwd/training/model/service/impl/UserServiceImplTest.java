package com.epam.jwd.training.model.service.impl;

import com.epam.jwd.training.exception.DaoException;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.dao.UserDao;
import com.epam.jwd.training.model.dao.impl.UserDaoImpl;
import com.epam.jwd.training.model.entity.Course;
import com.epam.jwd.training.model.entity.RoleType;
import com.epam.jwd.training.model.entity.User;
import com.epam.jwd.training.model.service.UserService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    private UserDao userDao;
    private UserService userService;
    private User user1;
    private User user2;
    private final List<User> users = new ArrayList<>();

    @Before
    public void setUp() {
        userDao = mock(UserDaoImpl.class);
        userService = new UserServiceImpl(userDao);
        Course course = Course.builder()
                .setId(1L)
                .setName("Course")
                .build();
        user1 = User.builder()
                .setId(1L)
                .setName("Name")
                .setSurname("Surname")
                .setEmail("email")
                .setRole(RoleType.USER)
                .setCourse(course)
                .setEnabled(true)
                .build();
        user2 = User.builder()
                .setId(2L)
                .setName("Name2")
                .setSurname("Surname2")
                .setEmail("email2")
                .setRole(RoleType.ADMIN)
                .setEnabled(true)
                .build();
        users.add(user1);
    }

    @Test
    public void test_findAllUsers_returnListOfAllUsers() throws DaoException, ServiceException {
        when(userDao.findAll()).thenReturn(users);
        List<User> actual = userService.findAll();
        assertEquals(users, actual);
    }

    @Test
    public void test_findById_returnOptionalOfUserWithSuchId() throws DaoException, ServiceException {
        Optional<User> expected = Optional.of(user1);
        when(userDao.findById(user1.getId())).thenReturn(expected);
        Optional<User> actual = userService.findById(1L);
        assertEquals(expected, actual);
    }

    @Test
    public void test_findByEmail_returnOptionalOfUserWithSuchEmail() throws DaoException, ServiceException {
        Optional<User> expected = Optional.of(user1);
        when(userDao.findByEmail(user1.getEmail())).thenReturn(expected);
        Optional<User> actual = userService.findByEmail("email");
        assertEquals(expected, actual);
    }

    @Test
    public void test_isHaveCourse_returnTrueWhenUserHasTheCourse() throws DaoException, ServiceException {
        when(userDao.isHaveCourse(user1.getId(), user1.getCourse().getId())).thenReturn(true);
        boolean actual = userService.isHaveCourse(1L, 1L);
        assertTrue(actual);
    }

    @Test
    public void test_isHaveCourse_returnFalseWhenUserHasNotTheCourse() throws DaoException, ServiceException {
        when(userDao.isHaveCourse(user1.getId(), 1L)).thenReturn(false);
        boolean actual = userService.isHaveCourse(1L, 1L);
        assertFalse(actual);
    }

    @Test
    public void test_changeUserStatus_returnTrueWhenStatusHasChanged() throws DaoException, ServiceException {
        when(userDao.changeUserStatus(user1.getId(), false)).thenReturn(true);
        boolean actual = userService.changeUserStatus(user1.getId(), false);
        assertTrue(actual);
    }

    //??
    @Test
    public void test_findByEmailAndPassword() throws DaoException, ServiceException {
        Optional<User> expected = Optional.of(user1);
        when(userDao.findByEmailAndPassword(anyString(), anyString())).thenReturn(expected);
        Optional<User> actual = userService.findByEmailAndPassword("email", "password");
        assertEquals(expected, actual);
    }

    //??
    @Test
    public void test_addUser() throws DaoException, ServiceException {
        when(userDao.addUser(any(User.class), anyString())).thenReturn(true);
        boolean actual = userService.addUser(user2, "password");
        assertTrue(actual);
    }

    @Test
    public void enrollOnCourse() throws DaoException, ServiceException {
        when(userDao.enrollCourse(user1, 1L)).thenReturn(true);
        boolean actual = userService.enrollOnCourse(user1, 1L);
        assertTrue(actual);
    }

}