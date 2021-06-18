package com.epam.jwd.training.model.dao;

import com.epam.jwd.training.model.entity.User;
import com.epam.jwd.training.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao extends BaseDao<User> {

    List<User> findAllUsersOnCourse(Long courseId) throws DaoException;

    Optional<User> findByEmail(String email) throws DaoException;

    boolean addUser(User user, String password) throws DaoException;

    boolean enrollCourse(User user, Long courseId) throws DaoException;

    boolean updateUserToAdmin(User user) throws DaoException;

    boolean updatePassword(String password, Long userId) throws DaoException;

    boolean updateNameAndSurname(User user) throws DaoException;

    boolean blockUser(Long id) throws DaoException;

    boolean unblockUser(Long id) throws DaoException;

}
