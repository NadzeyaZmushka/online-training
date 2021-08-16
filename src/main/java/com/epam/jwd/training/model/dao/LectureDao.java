package com.epam.jwd.training.model.dao;

import com.epam.jwd.training.model.entity.Lecture;
import com.epam.jwd.training.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * Lecture DAO interface. Extends {@link BaseDao} interface
 *
 * @author Nadzeya Zmushka
 */
public interface LectureDao extends BaseDao<Lecture> {

    /**
     * Connect with database.
     * Find all lectures on this course
     *
     * @param courseId the course id
     * @return List of lectures on course
     * @throws DaoException the dao exception
     */
    List<Lecture> findAllLecturesByCourseId(Long courseId) throws DaoException;

    /**
     * Connect with database.
     * Update lecture name
     *
     * @param lecture lecture
     * @return true if the action was successful
     * @throws DaoException the dao exception
     */
    boolean update(Lecture lecture) throws DaoException;

    /**
     * Connect with database.
     * Add new lecture.
     *
     * @param lecture lecture
     * @return true if the action was successful
     * @throws DaoException the dao exception
     */
    boolean addLecture(Lecture lecture) throws DaoException;

    /**
     * Connect with database.
     * Find lecture on course by id
     *
     * @param lectureId the lecture id
     * @param courseId  the course id
     * @return {@link Optional} with lecture
     * @throws DaoException the dao exception
     */
    Optional<Lecture> findLectureByIdAndCourseId(Long lectureId, Long courseId) throws DaoException;

}
