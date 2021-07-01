package com.epam.jwd.training.model.service;

import com.epam.jwd.training.model.entity.User;
import com.epam.jwd.training.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll() throws ServiceException;

    Optional<User> findById(Long id) throws ServiceException;

    boolean delete(Long id) throws ServiceException;

    List<User> findAllUsersOnCourse(Long courseId) throws ServiceException;

    Optional<User> findByEmail(String email) throws ServiceException;

    Optional<User> findByEmailAndPassword(String email, String password) throws ServiceException;

    boolean addUser(User user, String password) throws ServiceException;

    boolean enrollOnCourse(User user, Long courseId) throws ServiceException;

    boolean updateUserToAdmin(User user) throws ServiceException;

    boolean updatePassword(String password, User user) throws ServiceException;

    boolean updateNameAndSurname(String name, String surname, Long id) throws ServiceException;

    boolean blockUser(Long id) throws ServiceException;

    boolean unblockUser(Long id) throws ServiceException;

}
