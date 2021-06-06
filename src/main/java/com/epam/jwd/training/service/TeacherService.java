package com.epam.jwd.training.service;

import com.epam.jwd.training.entity.Teacher;
import com.epam.jwd.training.exception.DaoException;
import com.epam.jwd.training.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface TeacherService {

    List<Teacher> findAll() throws ServiceException;

    Optional<Teacher> findById(long id) throws ServiceException;

    boolean delete(long id) throws ServiceException;

    Optional<Teacher> findBySurname(String surname) throws ServiceException;

    public boolean save(Teacher teacher) throws ServiceException;

}
