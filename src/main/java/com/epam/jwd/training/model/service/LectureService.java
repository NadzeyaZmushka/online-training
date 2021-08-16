package com.epam.jwd.training.model.service;

import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.entity.Lecture;

import java.util.List;
import java.util.Optional;

/**
 * The interface lecture service
 *
 * @author Nadzeya Zmushka
 */
public interface LectureService {

    /**
     * @return List of all lectures
     * @throws ServiceException the service exception
     */
    List<Lecture> findAll() throws ServiceException;

    /**
     * @param id the lecture id
     * @return Optional<Lecture> with such id
     * @throws ServiceException the service exception
     */
    Optional<Lecture> findById(Long id) throws ServiceException;

    /**
     * Delete lecture with such id
     *
     * @param id the lecture id
     * @return boolean result
     * @throws ServiceException the service exception
     */
    boolean delete(Long id) throws ServiceException;

    /**
     * @param courseId the course id
     * @return List of lectures on this course
     * @throws ServiceException the service exception
     */
    List<Lecture> findAllLecturesByCourseId(Long courseId) throws ServiceException;

    /**
     * Update lecture name
     *
     * @param lecture {@link Lecture}
     * @return boolean result
     * @throws ServiceException the service exception
     */
    boolean update(Lecture lecture) throws ServiceException;

    /**
     * Add new lecture
     *
     * @param lecture {@link Lecture}
     * @return boolean result
     * @throws ServiceException the service exception
     */
    boolean addLecture(Lecture lecture) throws ServiceException;

    /**
     * @param id       the lecture id
     * @param courseId the course id
     * @return Optional<Lecture>
     * @throws ServiceException the service exception
     */
    Optional<Lecture> findLectureByIdAndCourseId(Long lectureId, Long courseId) throws ServiceException;

}
