package com.epam.jwd.training.model.dao;

import com.epam.jwd.training.model.entity.Teacher;
import com.epam.jwd.training.exception.DaoException;

import java.util.Optional;

/**
 * Teacher DAO interface. Extends {@link BaseDao} interface
 */
public interface TeacherDao extends BaseDao<Teacher> {

    /**
     * Connect with database.
     * Find teacher with such name and surname
     * @param name the teacher name
     * @param surname the teacher surname
     * @return {@link Optional} with teacher
     * @throws DaoException the dao exception
     */
    Optional<Teacher> findByNameAndSurname(String name, String surname) throws DaoException;

    /**
     * Connect with database.
     * Add new teacher
     * @param teacher teacher
     * @return true if the action was successful
     * @throws DaoException the dao exception
     */
    boolean addTeacher(Teacher teacher) throws DaoException;

}
