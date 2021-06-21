package com.epam.jwd.training.model.service.impl;

import com.epam.jwd.training.model.dao.TeacherDao;
import com.epam.jwd.training.model.dao.impl.TeacherDaoImpl;
import com.epam.jwd.training.model.entity.Teacher;
import com.epam.jwd.training.exception.DaoException;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.service.TeacherService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class TeacherServiceImpl implements TeacherService {

    public static final TeacherServiceImpl INSTANCE = new TeacherServiceImpl();

    private static final Logger LOGGER = LogManager.getLogger(TeacherServiceImpl.class);

    private final TeacherDao teacherDao = TeacherDaoImpl.getInstance();

    private TeacherServiceImpl() {
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

    @Override
    public boolean save(String name, String surname) throws ServiceException {
        boolean isSaved;
        Teacher teacher = Teacher.builder()
                .setName(name)
                .setSurname(surname)
                .build();
        try {
            isSaved = teacherDao.save(teacher);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isSaved;
    }

    public static TeacherServiceImpl getInstance() {
        return INSTANCE;
    }

}
