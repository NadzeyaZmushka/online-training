package com.epam.jwd.training.service.impl;

import com.epam.jwd.training.dao.CourseDao;
import com.epam.jwd.training.dao.impl.CourseDaoImpl;
import com.epam.jwd.training.entity.Course;
import com.epam.jwd.training.exception.DaoException;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.service.CourseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class CourseServiceImpl implements CourseService {

    public static final CourseServiceImpl INSTANCE = new CourseServiceImpl();

    private static final Logger LOGGER = LogManager.getLogger(CourseServiceImpl.class);

    private final CourseDao courseDao = CourseDaoImpl.INSTANCE;

    private CourseServiceImpl() {
    }

    @Override
    public List<Course> findAll() throws ServiceException {
        List<Course> courses;
        try {
            courses = courseDao.findAll();
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return courses;
    }

    @Override
    public Optional<Course> findById(long id) throws ServiceException {
        Optional<Course> courseOptional;
        try {
            courseOptional = courseDao.findById(id);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return courseOptional;
    }

    @Override
    public boolean delete(long id) throws ServiceException {
        boolean isDeleted;
        try {
            isDeleted = courseDao.delete(id);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isDeleted;
    }

    @Override
    public List<Course> findAllCoursesByTeacherId(long teacherId) throws ServiceException {
        List<Course> courses;
        try {
            courses = courseDao.findAllCoursesByTeacherId(teacherId);
        } catch (DaoException e) {
            LOGGER.error(e);
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
            LOGGER.error(e);
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
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isUpdate;
    }

    //???
    @Override
    public boolean updateDate(Course course) throws ServiceException {
        boolean isUpdate;
        try {
            isUpdate = courseDao.updateDate(course);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean save(Course course) throws ServiceException {
        boolean isSaved;
        try {
            isSaved = courseDao.save(course);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isSaved;
    }

}
