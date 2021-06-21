package com.epam.jwd.training.model.service.impl;

import com.epam.jwd.training.model.dao.LectureDao;
import com.epam.jwd.training.model.dao.impl.LectureDaoImpl;
import com.epam.jwd.training.model.entity.Lecture;
import com.epam.jwd.training.exception.DaoException;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.service.LectureService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class LectureServiceImpl implements LectureService {

    public static final LectureServiceImpl INSTANCE = new LectureServiceImpl();

    private static final Logger LOGGER = LogManager.getLogger(LectureServiceImpl.class);

    private final LectureDao lectureDao = LectureDaoImpl.getInstance();

    private LectureServiceImpl() {
    }

    @Override
    public List<Lecture> findAll() throws ServiceException {
        List<Lecture> lectures;
        try {
            lectures = lectureDao.findAll();
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return lectures;
    }

    @Override
    public Optional<Lecture> findById(Long id) throws ServiceException {
        Optional<Lecture> taskOptional;
        try {
            taskOptional = lectureDao.findById(id);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return taskOptional;
    }

    @Override
    public boolean delete(Long id) throws ServiceException {
        boolean isDeleted;
        try {
            isDeleted = lectureDao.delete(id);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isDeleted;
    }

    @Override
    public List<Lecture> findAllLecturesByCourseId(Long courseId) throws ServiceException {
        List<Lecture> lectures;
        try {
            lectures = lectureDao.findAllLecturesByCourseId(courseId);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return lectures;
    }

    @Override
    public boolean update(Lecture lecture) throws ServiceException {
        boolean isUpdate;
        try {
            isUpdate = lectureDao.update(lecture);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean save(Lecture lecture) throws ServiceException {
        boolean isSaved;
        try {
            isSaved = lectureDao.save(lecture);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isSaved;
    }

    @Override
    public Optional<Lecture> findLectureByIdAndCourseId(Long id, Long courseId) throws ServiceException {
        Optional<Lecture> lectureOptional;
        try {
            lectureOptional = lectureDao.findLectureByIdAndCourseId(id, courseId);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return lectureOptional;
    }

    public static LectureServiceImpl getInstance() {
        return INSTANCE;
    }

}
