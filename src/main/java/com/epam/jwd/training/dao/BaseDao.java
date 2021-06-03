package com.epam.jwd.training.dao;

import com.epam.jwd.training.entity.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface BaseDao<T extends BaseEntity> {

    List<T> findAll();

    Optional<T> findById(long id);

    T save(T entity);

    boolean delete(long id);

}
