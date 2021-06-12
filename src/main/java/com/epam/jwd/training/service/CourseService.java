package com.epam.jwd.training.service;

import com.epam.jwd.training.entity.Course;
import com.epam.jwd.training.entity.Teacher;
import com.epam.jwd.training.exception.DaoException;
import com.epam.jwd.training.exception.ServiceException;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Course> findAll() throws ServiceException;

    Optional<Course> findById(long id) throws ServiceException;

    boolean delete(long id) throws ServiceException;

    List<Course> findAllCoursesByTeacherId(long id) throws ServiceException;

    List<Course> findUserEnrolledByCourse(long userId) throws ServiceException;

    boolean updateHours(Course course) throws ServiceException;

    boolean updateCost(Course course) throws ServiceException;

    boolean updateDate(Course course) throws ServiceException;

    boolean save(String name, String description, String hours,
                 String start, String end, String cost, Teacher teacher) throws ServiceException;

}
