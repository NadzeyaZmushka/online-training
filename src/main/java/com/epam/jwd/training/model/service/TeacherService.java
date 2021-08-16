package com.epam.jwd.training.model.service;

import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.entity.Teacher;

import java.util.List;
import java.util.Optional;

/**
 * The interface teacher service
 *
 * @author Nadzeya Zmushka
 */
public interface TeacherService {

    /**
     * @return List of teachers
     * @throws ServiceException the service exception
     */
    List<Teacher> findAll() throws ServiceException;

    /**
     * @param id the teacher id
     * @return Optional<Teacher> with such id
     * @throws ServiceException the service exception
     */
    Optional<Teacher> findById(Long id) throws ServiceException;

    /**
     * Delete teacher
     *
     * @param id the teacher id
     * @return boolean result
     * @throws ServiceException the service exception
     */
    boolean delete(Long id) throws ServiceException;

    /**
     * @param name    the teacher name
     * @param surname the teacher surname
     * @return Optional<Teacher> with such name and surname
     * @throws ServiceException Optional<Teacher>
     */
    Optional<Teacher> findByNameAndSurname(String name, String surname) throws ServiceException;

    /**
     * Save new teacher
     *
     * @param teacher Teacher
     * @return boolean result
     * @throws ServiceException Optional<Teacher>
     */
    boolean addTeacher(Teacher teacher) throws ServiceException;

}
