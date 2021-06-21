package com.epam.jwd.training.model.service;

import com.epam.jwd.training.model.entity.Lecture;
import com.epam.jwd.training.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface LectureService {

    List<Lecture> findAll() throws ServiceException;

    Optional<Lecture> findById(Long id) throws ServiceException;

    boolean delete(Long id) throws ServiceException;

    List<Lecture> findAllLecturesByCourseId(Long courseId) throws ServiceException;

    boolean update(Lecture lecture) throws ServiceException;

    boolean save(Lecture lecture) throws ServiceException;

    Optional<Lecture> findLectureByIdAndCourseId(Long id, Long courseId) throws ServiceException;


}
