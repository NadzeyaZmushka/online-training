package com.epam.jwd.training.service;

import com.epam.jwd.training.entity.Task;
import com.epam.jwd.training.exception.DaoException;
import com.epam.jwd.training.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<Task> findAll() throws ServiceException;

    Optional<Task> findById(long id) throws ServiceException;

    boolean delete(long id) throws ServiceException;

    List<Task> findAllTasksByCourseId(long id) throws ServiceException;

    boolean update(Task task) throws ServiceException;

    public boolean save(Task task) throws ServiceException;

}
