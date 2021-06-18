package com.epam.jwd.training.model.service.impl;

import com.epam.jwd.training.model.dao.UserDao;
import com.epam.jwd.training.model.dao.impl.UserDaoImpl;
import com.epam.jwd.training.model.entity.User;
import com.epam.jwd.training.exception.DaoException;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    public static final UserServiceImpl INSTANCE = new UserServiceImpl();

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    private final UserDao userDao = UserDaoImpl.getInstance();

    private UserServiceImpl() {
    }

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
    public boolean enrollOnCourse(User user, Long courseId) throws ServiceException {
        boolean isEnrolled;
        try {
            isEnrolled = userDao.enrollCourse(user, courseId);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isEnrolled;
    }

    @Override
    public boolean updateUserToAdmin(User user) throws ServiceException {
        boolean isUpdate;
        try {
            isUpdate = userDao.updateUserToAdmin(user);
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

    @Override
    public boolean updateNameAndSurname(String name, String surname, long id) throws ServiceException {
        boolean isUpdate;
        try {
            User user = User.builder()
                    .setId(id)
                    .setName(name)
                    .setSurname(surname)
                    .build();
            isUpdate = userDao.updateNameAndSurname(user);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean blockUser(long id) throws ServiceException {
        boolean isBlocked;
        try {
            isBlocked = userDao.blockUser(id);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isBlocked;
    }

    @Override
    public boolean unblockUser(long id) throws ServiceException {
        boolean isUnBlocked;
        try {
            isUnBlocked = userDao.unblockUser(id);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isUnBlocked;
    }

    public static UserServiceImpl getInstance() {
        return INSTANCE;
    }

}
