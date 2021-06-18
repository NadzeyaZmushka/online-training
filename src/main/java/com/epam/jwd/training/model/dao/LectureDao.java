package com.epam.jwd.training.model.dao;

import com.epam.jwd.training.model.entity.Lecture;
import com.epam.jwd.training.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface LectureDao extends BaseDao<Lecture> {

    List<Lecture> findAllLecturesByCourseId(Long courseId) throws DaoException;

    boolean update(Lecture lecture) throws DaoException;

    boolean save(Lecture lecture) throws DaoException;

    Optional<Lecture> findLectureByIdAndCourseId(Long lectureId, Long courseId) throws DaoException;

}
