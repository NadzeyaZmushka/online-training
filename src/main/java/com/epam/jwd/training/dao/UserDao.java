package com.epam.jwd.training.dao;

import com.epam.jwd.training.entity.User;

import java.util.List;

public interface UserDao extends BaseDao<User> {

    List<User> findAllUsersByCourseId(long id);
}
