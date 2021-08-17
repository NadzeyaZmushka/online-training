package com.epam.jwd.training.model.service;

import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * The interface user service
 *
 * @author Nadzeya Zmushka
 */
public interface UserService {

    /**
     * @return List of users
     * @throws ServiceException the service exception
     */
    List<User> findAll() throws ServiceException;

    /**
     * @param userId the user id
     * @return Optional<User>
     * @throws ServiceException the service exception
     */
    Optional<User> findById(Long userId) throws ServiceException;

    List<User> findAllUsersOnCourse() throws ServiceException;

    /**
     * @param email the user email
     * @return Optional<User> with such email
     * @throws ServiceException the service exception
     */
    Optional<User> findByEmail(String email) throws ServiceException;

    /**
     * @param email    the user email
     * @param password the user password
     * @return Optional<User> with such email and password
     * @throws ServiceException the service exception
     */
    Optional<User> findByEmailAndPassword(String email, String password) throws ServiceException;

    /**
     * @param user     the User
     * @param password the user password
     * @return true if the action was successful
     * @throws ServiceException the service exception
     */
    boolean addUser(User user, String password) throws ServiceException;

    /**
     * @param user     User
     * @param courseId the course id
     * @return true if the action was successful
     * @throws ServiceException the service exception
     */
    boolean enrollOnCourse(User user, Long courseId) throws ServiceException;

    /**
     *
     * @param user user
     * @param courseId the course id
     * @return true if the action was successful
     * @throws ServiceException the service exception
     */
    boolean unEnrollOnCourse(User user, Long courseId) throws ServiceException;

    /**
     * @param userId   the user id
     * @param courseId the course id
     * @return true if the action was successful
     * @throws ServiceException the service exception
     */
    boolean isHaveCourse(Long userId, Long courseId) throws ServiceException;

    /**
     * @param user User
     * @return true if the action was successful
     * @throws ServiceException the service exception
     */
    boolean updateUserToAdmin(User user) throws ServiceException;

    /**
     * @param password the user password
     * @param user     User
     * @return true if the action was successful
     * @throws ServiceException the service exception
     */
    boolean updatePassword(String password, User user) throws ServiceException;

    /**
     * @param name    the user name
     * @param surname thr user surname
     * @param id      the user id
     * @return true if the action was successful
     * @throws ServiceException the service exception
     */
    boolean updateNameAndSurname(String name, String surname, Long id) throws ServiceException;

    /**
     * @param id        the user id
     * @param isEnabled the user enabled
     * @return true if the action was successful
     * @throws ServiceException the service exception
     */
    boolean changeUserStatus(Long id, boolean isEnabled) throws ServiceException;

}
