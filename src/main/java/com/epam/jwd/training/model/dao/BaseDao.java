package com.epam.jwd.training.model.dao;

import com.epam.jwd.training.model.entity.BaseEntity;
import com.epam.jwd.training.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface BaseDao<T extends BaseEntity> {

    List<T> findAll() throws DaoException;

    Optional<T> findById(Long id) throws DaoException;

    boolean delete(Long id) throws DaoException;

}
