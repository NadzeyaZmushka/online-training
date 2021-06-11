package com.epam.jwd.training.dao;

import com.epam.jwd.training.entity.Course;
import com.epam.jwd.training.exception.DaoException;

import java.util.List;

public interface CourseDao extends BaseDao<Course> {

    List<Course> findAllCoursesByTeacherId(long id) throws DaoException;

    boolean updateHours(Course course) throws DaoException;

    boolean updateCost(Course course) throws DaoException;

    boolean updateDate(Course course) throws DaoException;

    boolean save(Course course) throws DaoException;

}
