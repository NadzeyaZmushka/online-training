package com.epam.jwd.training.model.service.impl;

import com.epam.jwd.training.exception.DaoException;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.dao.CourseDao;
import com.epam.jwd.training.model.entity.Course;
import com.epam.jwd.training.model.service.CourseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * Class course service
 *
 * @author Nadzeya Zmushka
 */
public class CourseServiceImpl implements CourseService {

    private static final Logger LOGGER = LogManager.getLogger(CourseServiceImpl.class);

    private final CourseDao courseDao;

    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public List<Course> findAll() throws ServiceException {
        List<Course> courses;
        try {
            courses = courseDao.findAll();
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
        return courses;
    }

    @Override
    public Optional<Course> findById(Long id) throws ServiceException {
        Optional<Course> courseOptional;
        try {
            courseOptional = courseDao.findById(id);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
        return courseOptional;
    }

    @Override
    public boolean delete(Long id) throws ServiceException {
        boolean isDeleted;
        try {
            isDeleted = courseDao.delete(id);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
        return isDeleted;
    }

    @Override
    public List<Course> findUserEnrolledByCourse(Long userId) throws ServiceException {
        List<Course> courses;
        try {
            courses = courseDao.findUserEnrolledByCourse(userId);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
        return courses;
    }

    @Override
    public boolean updateHours(Course course) throws ServiceException {
        boolean isUpdate;
        try {
            isUpdate = courseDao.updateHours(course);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean updateCourseName(Course course) throws ServiceException {
        boolean isUpdate;
        try {
            isUpdate = courseDao.updateCourseName(course);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean updateDescription(Course course) throws ServiceException {
        boolean isUpdate;
        try {
            isUpdate = courseDao.updateDescription(course);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean updateCost(Course course) throws ServiceException {
        boolean isUpdate;
        try {
            isUpdate = courseDao.updateCost(course);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean updateDate(Course course) throws ServiceException {
        boolean isUpdate;
        try {
            isUpdate = courseDao.updateDate(course);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean updateTeacher(Course course) throws ServiceException {
        boolean isUpdate;
        try {
            isUpdate = courseDao.updateTeacher(course);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean addCourse(Course course) throws ServiceException {
        boolean isSave;
        try {
            isSave = courseDao.addCourse(course);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
        return isSave;
    }

}
