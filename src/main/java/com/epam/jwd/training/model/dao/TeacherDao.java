package com.epam.jwd.training.model.dao;

import com.epam.jwd.training.model.entity.Teacher;
import com.epam.jwd.training.exception.DaoException;

import java.util.Optional;

public interface TeacherDao extends BaseDao<Teacher> {

    Optional<Teacher> findByNameAndSurname(String name, String surname) throws DaoException;

    boolean save(Teacher teacher) throws DaoException;

}
