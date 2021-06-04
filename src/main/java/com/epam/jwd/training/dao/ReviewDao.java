package com.epam.jwd.training.dao;

import com.epam.jwd.training.entity.Review;
import com.epam.jwd.training.exception.DaoException;

import java.util.Optional;

public interface ReviewDao extends BaseDao<Review> {

    public boolean save(Review review) throws DaoException;

    Optional<Review> findReviewByUserId(long id) throws DaoException;

}
