package com.epam.jwd.training.service;

import com.epam.jwd.training.entity.Lecture;
import com.epam.jwd.training.exception.DaoException;
import com.epam.jwd.training.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface LectureService {

    List<Lecture> findAll() throws ServiceException;

    Optional<Lecture> findById(long id) throws ServiceException;

    boolean delete(long id) throws ServiceException;

    List<Lecture> findAllLecturesByCourseId(long id) throws ServiceException;

    boolean update(Lecture lecture) throws ServiceException;

    boolean save(Lecture lecture) throws ServiceException;

    Optional<Lecture> findLectureByIdAndCourseId(long id, long courseId) throws ServiceException;


}
