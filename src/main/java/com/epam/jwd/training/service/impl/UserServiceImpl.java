package com.epam.jwd.training.service.impl;

import com.epam.jwd.training.dao.UserDao;
import com.epam.jwd.training.dao.impl.UserDaoImpl;
import com.epam.jwd.training.entity.User;
import com.epam.jwd.training.exception.DaoException;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    private final UserDao userDao = UserDaoImpl.INSTANCE;

    @Override
    public List<User> findAll() throws ServiceException {
        List<User> users;
        try {
            users = userDao.findAll();
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return users;
    }

    @Override
    public Optional<User> findById(long id) throws ServiceException {
        Optional<User> user = Optional.empty();
        try {
            user = userDao.findById(id);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return user;
    }

    //???
    @Override
    public boolean delete(long id) throws ServiceException {
        boolean isDeleted;
        try {
            isDeleted = userDao.delete(id);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isDeleted;
    }

    @Override
    public List<User> findAllUsersOnCourse(long courseId) throws ServiceException {
        List<User> users;
        try {
            users = userDao.findAllUsersOnCourse(courseId);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return users;
    }

    @Override
    public Optional<User> findByEmail(String email) throws ServiceException {
        Optional<User> user;
        try {
            user = userDao.findByEmail(email);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public boolean addUser(User user, String password) throws ServiceException {
        //todo:
        return false;
    }

    @Override
    public boolean updateUserToAdmin(long userId) throws ServiceException {
        boolean isUpdate;
        try {
            isUpdate = userDao.updateUserToAdmin(userId);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean updatePassword(String password, long userId) throws ServiceException {
        //todo:

        return false;
    }

}
