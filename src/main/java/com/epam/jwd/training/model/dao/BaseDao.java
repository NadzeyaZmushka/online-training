package com.epam.jwd.training.model.dao;

import com.epam.jwd.training.exception.DaoException;
import com.epam.jwd.training.model.entity.BaseEntity;

import java.util.List;
import java.util.Optional;

/**
 * Base DAO interface should connect with database, do CRUD actions
 *
 * @param <T>
 * @author Nadzeya Zmushka
 */
public interface BaseDao<T extends BaseEntity> {

    /**
     * Connect with database.
     * Return all entities
     *
     * @return List of all entities
     * @throws DaoException the dao exception
     */
    List<T> findAll() throws DaoException;

    /**
     * Connect with database.
     * Find entity with such id
     *
     * @param id the entity id
     * @return {@link Optional} with entity
     * @throws DaoException the dao exception
     */
    Optional<T> findById(Long id) throws DaoException;

    /**
     * Connect with database.
     * Delete entity
     *
     * @param id the entity id
     * @return true if the action was successful
     * @throws DaoException he dao exception
     */
    boolean delete(Long id) throws DaoException;

}
