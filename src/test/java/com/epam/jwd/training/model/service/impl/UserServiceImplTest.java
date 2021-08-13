package com.epam.jwd.training.model.service.impl;

import com.epam.jwd.training.exception.DaoException;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.dao.UserDao;
import com.epam.jwd.training.model.dao.impl.UserDaoImpl;
import com.epam.jwd.training.model.entity.RoleType;
import com.epam.jwd.training.model.entity.User;
import org.junit.BeforeClass;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    private static UserDao mockUserDao;
    private static final User expected = User.builder()
            .setId(1L)
            .setName("Name")
            .setSurname("Surname")
            .setEmail("email")
            .setRole(RoleType.USER)
            .setEnabled(true)
            .build();

    @BeforeClass
    public static void init() {
        mockUserDao = mock(UserDaoImpl.class);
        Whitebox.setInternalState(UserDaoImpl.class, "INSTANCE", mockUserDao);
    }

    @Test
    public void test_findById() throws DaoException, ServiceException {
        when(mockUserDao.findById(expected.getId())).thenReturn(Optional.of(expected));

        assertTrue(UserServiceImpl.getInstance().findById(expected.getId()).isPresent());
    }

    @Test
    public void test_findByEmail() throws DaoException, ServiceException {
        when(mockUserDao.findByEmail(expected.getEmail())).thenReturn(Optional.of(expected));

        assertTrue(UserServiceImpl.getInstance().findByEmail(expected.getEmail()).isPresent());
    }

    @Test
    public void changeUserStatus() throws DaoException, ServiceException {
        when(mockUserDao.changeUserStatus(expected.getId(), false))
                .thenReturn(true);

        assertTrue(UserServiceImpl.getInstance().changeUserStatus(expected.getId(),false));
    }
}