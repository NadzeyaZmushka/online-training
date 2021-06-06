package com.epam.jwd.training.service;

import com.epam.jwd.training.entity.User;
import com.epam.jwd.training.exception.DaoException;
import com.epam.jwd.training.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll() throws ServiceException;

    Optional<User> findById(long id) throws ServiceException;

    boolean delete(long id) throws ServiceException;

    List<User> findAllUsersOnCourse(long courseId) throws ServiceException;

    Optional<User> findByEmail(String email) throws ServiceException;

    boolean addUser(User user, String password) throws ServiceException;

    boolean updateUserToAdmin(long userId) throws ServiceException;

    boolean updatePassword(String password, long userId) throws ServiceException;
}
