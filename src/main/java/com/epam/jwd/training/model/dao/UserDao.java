package com.epam.jwd.training.model.dao;

import com.epam.jwd.training.model.entity.User;
import com.epam.jwd.training.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * User DAO interface. Extends {@link BaseDao} interface
 *
 * @author Nadzeya Zmushka
 */
public interface UserDao extends BaseDao<User> {

    /**
     * Connect with database. Return all users on courses
     *
     * @return List of users on courses
     * @throws DaoException the dao exception
     */
    List<User> findAllUsersOnCourse() throws DaoException;

    /**
     * Connect with database.
     * Find user with such email.
     *
     * @param email the user email
     * @return {@link Optional} with new users
     * @throws DaoException the dao exception
     */
    Optional<User> findByEmail(String email) throws DaoException;

    /**
     * Connect with database.
     * Find user with such email and password
     *
     * @param email    the user email
     * @param password the user password
     * @return {@link Optional} with user
     * @throws DaoException the dao exception
     */
    Optional<User> findByEmailAndPassword(String email, String password) throws DaoException;

    /**
     * Connect with database.
     * Add new user
     *
     * @param user     user
     * @param password the User password
     * @return true if the action was successful
     * @throws DaoException the dao exception
     */
    boolean addUser(User user, String password) throws DaoException;

    /**
     * Connect with database.
     * Enroll user on course with this id
     *
     * @param user     user
     * @param courseId the course id
     * @return true if the action was successful
     * @throws DaoException the dao exception
     */
    boolean enrollCourse(User user, Long courseId) throws DaoException;

    /**
     * @param userId   the user id
     * @param courseId the course id
     * @return true if user already has this course
     * @throws DaoException the dao exception
     */
    boolean isHaveCourse(Long userId, Long courseId) throws DaoException;

    /**
     * Connect with database.
     * Update user role to admin role
     *
     * @param user user
     * @return true if the action was successful
     * @throws DaoException the dao exception
     */
    boolean updateUserToAdmin(User user) throws DaoException;

    /**
     * Connect with database.
     * Update user password.
     *
     * @param password the user password
     * @param userId   the user id
     * @return true if the action was successful
     * @throws DaoException the dao exception
     */
    boolean updatePassword(String password, Long userId) throws DaoException;

    /**
     * Connect with database.
     * Update user name and surname.
     *
     * @param user user
     * @return true if the action was successful
     * @throws DaoException the dao exception
     */
    boolean updateNameAndSurname(User user) throws DaoException;

    /**
     * Connect with database.
     * change user status
     *
     * @param id        user id
     * @param isEnabled is user enabled
     * @return true if the action was successful
     * @throws DaoException the dao exception
     */
    boolean changeUserStatus(Long id, boolean isEnabled) throws DaoException;


}
