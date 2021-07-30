package com.epam.jwd.training.model.service;

import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Course> findAll() throws ServiceException;

    Optional<Course> findById(Long id) throws ServiceException;

    boolean delete(Long id) throws ServiceException;

    List<Course> findAllCoursesByTeacherId(Long id) throws ServiceException;

    List<Course> findUserEnrolledByCourse(Long userId) throws ServiceException;

    boolean updateHours(Course course) throws ServiceException;

    boolean updateCourseName(Course course) throws ServiceException;

    boolean updateDescription(Course course) throws ServiceException;

    boolean updateCost(Course course) throws ServiceException;

    boolean updateDate(Course course) throws ServiceException;

    boolean updateTeacher(Course course) throws ServiceException;

    boolean save(Course course) throws ServiceException;

}
