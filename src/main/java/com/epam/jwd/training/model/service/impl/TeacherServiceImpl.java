package com.epam.jwd.training.model.service.impl;

import com.epam.jwd.training.exception.DaoException;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.dao.TeacherDao;
import com.epam.jwd.training.model.entity.Teacher;
import com.epam.jwd.training.model.service.TeacherService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * Class teacher service
 *
 * @author Nadzeya Zmushka
 */
public class TeacherServiceImpl implements TeacherService {

    private static final Logger LOGGER = LogManager.getLogger(TeacherServiceImpl.class);

    private final TeacherDao teacherDao;

    public TeacherServiceImpl(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

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
    public Optional<Teacher> findById(Long id) throws ServiceException {
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
    public boolean delete(Long id) throws ServiceException {
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
    public Optional<Teacher> findByNameAndSurname(String name, String surname) throws ServiceException {
        Optional<Teacher> teacherOptional;
        try {
            teacherOptional = teacherDao.findByNameAndSurname(name, surname);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return teacherOptional;
    }

    @Override
    public boolean addTeacher(Teacher teacher) throws ServiceException {
        boolean isSaved;
        try {
            isSaved = teacherDao.addTeacher(teacher);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isSaved;
    }

}
