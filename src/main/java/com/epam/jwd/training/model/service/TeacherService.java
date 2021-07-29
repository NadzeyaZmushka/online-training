package com.epam.jwd.training.model.service;

import com.epam.jwd.training.model.entity.Teacher;
import com.epam.jwd.training.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface TeacherService {

    List<Teacher> findAll() throws ServiceException;

    Optional<Teacher> findById(Long id) throws ServiceException;

    boolean delete(Long id) throws ServiceException;

    Optional<Teacher> findByNameAndSurname(String name, String surname) throws ServiceException;

    public boolean save(Teacher teacher) throws ServiceException;

}
