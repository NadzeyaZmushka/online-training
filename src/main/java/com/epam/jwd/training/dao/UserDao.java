package com.epam.jwd.training.dao;

import com.epam.jwd.training.entity.User;
import com.epam.jwd.training.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao extends BaseDao<User> {

    List<User> findAllUsersOnCourse(long id) throws DaoException;

    Optional<User> findByEmail(String email) throws DaoException;

    boolean addUser(User user, String password) throws DaoException;

    boolean updatePassword(String password, long userId) throws DaoException;

    boolean blockUser(long id) throws DaoException;

    boolean unblockUser(long id) throws DaoException;

    boolean updateUserToAdmin(User user) throws DaoException;

}
