package com.epam.jwd.training.service;

import com.epam.jwd.training.entity.Course;
import com.epam.jwd.training.exception.DaoException;
import com.epam.jwd.training.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Course> findAll() throws ServiceException;

    Optional<Course> findById(long id) throws ServiceException;

    boolean delete(long id) throws ServiceException;

    List<Course> findAllCoursesByTeacherId(long id) throws ServiceException;

    boolean updateCost(Course course) throws ServiceException;

    boolean updateDate(Course course, long id) throws ServiceException;

    public boolean save(Course course) throws ServiceException;

}
