package com.epam.jwd.training.model.service;

import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.entity.Course;

import java.util.List;
import java.util.Optional;

/**
 * The interface course service
 *
 * @author Nadzeya Zmushka
 */
public interface CourseService {

    /**
     * @return List of all courses
     * @throws ServiceException the service exception
     */
    List<Course> findAll() throws ServiceException;

    /**
     * @param id the course id
     * @return Optional<Course> with such id
     * @throws ServiceException the service exception
     */
    Optional<Course> findById(Long id) throws ServiceException;

    /**
     * Delete course
     *
     * @param id the course id
     * @return boolean result
     * @throws ServiceException the service exception
     */
    boolean delete(Long id) throws ServiceException;

    /**
     * @param id the teacher id
     * @return List of courses by teacher
     * @throws ServiceException the service exception
     */
    List<Course> findAllCoursesByTeacherId(Long id) throws ServiceException;

    /**
     * @param userId the user id
     * @return List of courses
     * @throws ServiceException the service exception
     */
    List<Course> findUserEnrolledByCourse(Long userId) throws ServiceException;

    /**
     * Update course hours
     *
     * @param course {@link Course}
     * @return boolean result
     * @throws ServiceException the service exception
     */
    boolean updateHours(Course course) throws ServiceException;

    /**
     * Update course name
     *
     * @param course {@link Course}
     * @return boolean result
     * @throws ServiceException the service exception
     */
    boolean updateCourseName(Course course) throws ServiceException;

    /**
     * Update course description
     *
     * @param course {@link Course}
     * @return boolean result
     * @throws ServiceException the service exception
     */
    boolean updateDescription(Course course) throws ServiceException;

    /**
     * Update course cost
     *
     * @param course {@link Course}
     * @return boolean result
     * @throws ServiceException the service exception
     */
    boolean updateCost(Course course) throws ServiceException;

    /**
     * Update start and end of course
     *
     * @param course {@link Course}
     * @return boolean result
     * @throws ServiceException the service exception
     */
    boolean updateDate(Course course) throws ServiceException;

    /**
     * Update teacher on course
     *
     * @param course {@link Course}
     * @return boolean result
     * @throws ServiceException the service exception
     */
    boolean updateTeacher(Course course) throws ServiceException;

    /**
     * Add new course
     *
     * @param course {@link Course}
     * @return boolean result
     * @throws ServiceException the service exception
     */
    boolean addCourse(Course course) throws ServiceException;

}
