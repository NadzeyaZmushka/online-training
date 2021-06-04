package com.epam.jwd.training.dao;

import com.epam.jwd.training.entity.BaseEntity;
import com.epam.jwd.training.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface BaseDao<T extends BaseEntity> {

    List<T> findAll() throws DaoException;

    Optional<T> findById(long id) throws DaoException;

    boolean delete(long id) throws DaoException;

}
