package com.epam.jwd.training.dao;

import com.epam.jwd.training.entity.Task;

import java.util.List;

public interface TaskDao extends BaseDao<Task> {

    List<Task> findAllTasksByCourseId(long id);

    Task update(Task task);

}
