package com.epam.jwd.training.model.service.impl;

import com.epam.jwd.training.exception.DaoException;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.dao.UserDao;
import com.epam.jwd.training.model.dao.impl.UserDaoImpl;
import com.epam.jwd.training.model.entity.User;
import com.epam.jwd.training.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    public static final UserServiceImpl INSTANCE = new UserServiceImpl();

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

//    private static final BCrypt.Hasher HASHER = BCrypt.withDefaults();
//    private static final BCrypt.Verifyer VERIFYER = BCrypt.verifyer();

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
    public Optional<User> findById(Long id) throws ServiceException {
        Optional<User> user;
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
    public boolean delete(Long id) throws ServiceException {
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
    public List<User> findAllUsersOnCourse() throws ServiceException {
        List<User> users;
        try {
            users = userDao.findAllUsersOnCourse();
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
    public Optional<User> findByEmailAndPassword(String email, String password) throws ServiceException {
        Optional<User> user;
        try {
            String encryptedPassword = Base64.getEncoder().encodeToString(password.getBytes());
            user = userDao.findByEmailAndPassword(email, encryptedPassword);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return user;
    }

    //??
    @Override
    public boolean addUser(User user, String password) throws ServiceException {
        boolean isAdd;
        try {
            String encryptedPassword = Base64.getEncoder().encodeToString(password.getBytes());
            isAdd = userDao.addUser(user, encryptedPassword);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isAdd;
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
    public boolean isHaveCourse(Long userId, Long courseId) throws ServiceException {
        boolean isHaveCourse;
        try {
            isHaveCourse = userDao.isHaveCourse(userId, courseId);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isHaveCourse;
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

    //??
    @Override
    public boolean updatePassword(String password, User user) throws ServiceException {
        boolean isUpdate;
        try {
            String encryptedPassword = Base64.getEncoder().encodeToString(password.getBytes());
            isUpdate = userDao.updatePassword(encryptedPassword, user.getId());
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean updateNameAndSurname(String name, String surname, Long id) throws ServiceException {
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
    public boolean changeUserStatus(Long id, boolean isEnabled) throws ServiceException {
        boolean isChanged;
        try {
            isChanged = userDao.changeUserStatus(id, isEnabled);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isChanged;
    }

//    @Override
//    public boolean blockUser(Long id) throws ServiceException {
//        boolean isBlocked;
//        try {
//            isBlocked = userDao.blockUser(id);
//        } catch (DaoException e) {
//            LOGGER.error(e);
//            throw new ServiceException(e);
//        }
//        return isBlocked;
//    }
//
//    @Override
//    public boolean unblockUser(Long id) throws ServiceException {
//        boolean isUnBlocked;
//        try {
//            isUnBlocked = userDao.unblockUser(id);
//        } catch (DaoException e) {
//            LOGGER.error(e);
//            throw new ServiceException(e);
//        }
//        return isUnBlocked;
//    }

    public static UserServiceImpl getInstance() {
        return INSTANCE;
    }

}
