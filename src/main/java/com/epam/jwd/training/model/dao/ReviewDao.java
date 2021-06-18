package com.epam.jwd.training.model.dao;

import com.epam.jwd.training.model.entity.Review;
import com.epam.jwd.training.exception.DaoException;

import java.util.Optional;

public interface ReviewDao extends BaseDao<Review> {

    boolean save(Review review) throws DaoException;

    Optional<Review> findReviewByUserId(Long userId) throws DaoException;

}
