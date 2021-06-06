package com.epam.jwd.training.service.impl;

import com.epam.jwd.training.dao.TaskDao;
import com.epam.jwd.training.dao.impl.TaskDaoImpl;
import com.epam.jwd.training.entity.Task;
import com.epam.jwd.training.exception.DaoException;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.service.TaskService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class TaskServiceImpl implements TaskService {

    private static final Logger LOGGER = LogManager.getLogger(TaskServiceImpl.class);

    private final TaskDao taskDao = TaskDaoImpl.INSTANCE;

    @Override
    public List<Task> findAll() throws ServiceException {
        List<Task> tasks;
        try {
            tasks = taskDao.findAll();
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return tasks;
    }

    @Override
    public Optional<Task> findById(long id) throws ServiceException {
        Optional<Task> taskOptional;
        try {
            taskOptional = taskDao.findById(id);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return taskOptional;
    }

    @Override
    public boolean delete(long id) throws ServiceException {
        boolean isDeleted;
        try {
            isDeleted = taskDao.delete(id);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isDeleted;
    }

    @Override
    public List<Task> findAllTasksByCourseId(long courseId) throws ServiceException {
        List<Task> tasks;
        try {
            tasks = taskDao.findAllTasksByCourseId(courseId);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return tasks;
    }

    @Override
    public boolean update(Task task) throws ServiceException {
        boolean isUpdate;
        try {
            isUpdate = taskDao.update(task);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean save(Task task) throws ServiceException {
        boolean isSaved;
        try {
            isSaved = taskDao.save(task);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isSaved;
    }

}
