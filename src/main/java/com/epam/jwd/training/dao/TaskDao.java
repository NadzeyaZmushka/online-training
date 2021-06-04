package com.epam.jwd.training.dao;

import com.epam.jwd.training.entity.Task;
import com.epam.jwd.training.exception.DaoException;

import java.util.List;

public interface TaskDao extends BaseDao<Task> {

    List<Task> findAllTasksByCourseId(long id) throws DaoException;

    boolean update(Task task) throws DaoException;

    public boolean save(Task task) throws DaoException;

}
