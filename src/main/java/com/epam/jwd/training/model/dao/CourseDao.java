package com.epam.jwd.training.model.dao;

import com.epam.jwd.training.model.entity.Course;
import com.epam.jwd.training.exception.DaoException;

import java.util.List;

public interface CourseDao extends BaseDao<Course> {

    List<Course> findAllCoursesByTeacherId(Long teacherId) throws DaoException;

    List<Course> findUserEnrolledByCourse(Long userId) throws DaoException;

    boolean updateHours(Course course) throws DaoException;

    boolean updateCourseName(Course course) throws DaoException;

    boolean updateDescription(Course course) throws DaoException;

    boolean updateCost(Course course) throws DaoException;

    boolean updateDate(Course course) throws DaoException;

    boolean save(Course course) throws DaoException;

}
