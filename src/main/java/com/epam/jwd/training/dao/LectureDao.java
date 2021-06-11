package com.epam.jwd.training.dao;

import com.epam.jwd.training.entity.Lecture;
import com.epam.jwd.training.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface LectureDao extends BaseDao<Lecture> {

    List<Lecture> findAllLecturesByCourseId(long id) throws DaoException;

    boolean update(Lecture lecture) throws DaoException;

    boolean save(Lecture lecture) throws DaoException;

    Optional<Lecture> findLectureByIdAndCourseId(long id, long courseId) throws DaoException;

}
