package com.epam.jwd.training.service.impl;

import com.epam.jwd.training.dao.TeacherDao;
import com.epam.jwd.training.dao.impl.TeacherDaoImpl;
import com.epam.jwd.training.entity.Teacher;
import com.epam.jwd.training.exception.DaoException;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.service.TeacherService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class TeacherServiceImpl implements TeacherService {

    private static final Logger LOGGER = LogManager.getLogger(TeacherServiceImpl.class);

    private final TeacherDao teacherDao = TeacherDaoImpl.INSTANCE;

    @Override
    public List<Teacher> findAll() throws ServiceException {
        List<Teacher> teachers;
        try {
            teachers = teacherDao.findAll();
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return teachers;
    }

    @Override
    public Optional<Teacher> findById(long id) throws ServiceException {
        Optional<Teacher> teacherOptional;
        try {
            teacherOptional = teacherDao.findById(id);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return teacherOptional;
    }

    @Override
    public boolean delete(long id) throws ServiceException {
        boolean isDeleted;
        try {
            isDeleted = teacherDao.delete(id);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isDeleted;
    }

    @Override
    public Optional<Teacher> findBySurname(String surname) throws ServiceException {
        Optional<Teacher> teacherOptional;
        try {
            teacherOptional = teacherDao.findBySurname(surname);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return teacherOptional;
    }

    //??
    @Override
    public boolean save(Teacher teacher) throws ServiceException {
        boolean isSaved;
        try {
            isSaved = teacherDao.save(teacher);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isSaved;
    }

}