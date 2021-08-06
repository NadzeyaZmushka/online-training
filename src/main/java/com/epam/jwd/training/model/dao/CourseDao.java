package com.epam.jwd.training.model.dao;

import com.epam.jwd.training.exception.DaoException;
import com.epam.jwd.training.model.entity.Course;

import java.util.List;

/**
 * Course DAO interface. Extends {@link BaseDao} interface
 *
 * @author Nadzeya Zmushka
 */
public interface CourseDao extends BaseDao<Course> {

    /**
     * Connect with database.
     * Find all courses by teacher
     *
     * @param teacherId the teacher id
     * @return List of courses
     * @throws DaoException the dao exception
     */
    List<Course> findAllCoursesByTeacherId(Long teacherId) throws DaoException;

    /**
     * Connect with database.
     * Find user enrolled by course
     *
     * @param userId the user id
     * @return list of courses
     * @throws DaoException the dao exception
     */
    List<Course> findUserEnrolledByCourse(Long userId) throws DaoException;

    /**
     * Connect with database.
     * Update course hours
     *
     * @param course course
     * @return true if the action was successful
     * @throws DaoException the dao exception
     */
    boolean updateHours(Course course) throws DaoException;

    /**
     * Connect with database.
     * Update course name.
     *
     * @param course course
     * @return true if the action was successful
     * @throws DaoException the dao exception
     */
    boolean updateCourseName(Course course) throws DaoException;

    /**
     * Connect with database.
     * Update course description
     *
     * @param course course
     * @return true if the action was successful
     * @throws DaoException the dao exception
     */
    boolean updateDescription(Course course) throws DaoException;

    /**
     * Connect with database.
     * Update course cost
     *
     * @param course course
     * @return true if the action was successful
     * @throws DaoException the dao exception
     */
    boolean updateCost(Course course) throws DaoException;

    /**
     * Connect with database.
     * Update start and end of course.
     *
     * @param course course
     * @return true if the action was successful
     * @throws DaoException the dao exception
     */
    boolean updateDate(Course course) throws DaoException;

    /**
     * Connect with database.
     * Update teacher on this course.
     *
     * @param course course
     * @return true if the action was successful
     * @throws DaoException the dao exception
     */
    boolean updateTeacher(Course course) throws DaoException;

    /**
     * Connect with database.
     * Add new course.
     *
     * @param course course
     * @return true if the action was successful
     * @throws DaoException the dao exception
     */
    boolean save(Course course) throws DaoException;

}
