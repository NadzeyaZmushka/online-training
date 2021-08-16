package com.epam.jwd.training.model.dao;

import com.epam.jwd.training.exception.DaoException;
import com.epam.jwd.training.model.entity.Review;

import java.util.Optional;

/**
 * Review DAO interface. Extends {@link BaseDao} interface
 *
 * @author Nadzeya Zmushka
 */
public interface ReviewDao extends BaseDao<Review> {

    /**
     * Connect with database.
     * Add new review
     *
     * @param review review
     * @return true if the action was successful
     * @throws DaoException the dao exception
     */
    boolean addReview(Review review) throws DaoException;

    /**
     * Connect with database.
     *
     * @param userId the user id
     * @return {@link Optional} with user
     * @throws DaoException the dao exception
     */
    Optional<Review> findReviewByUserId(Long userId) throws DaoException;

    /**
     * @param reviewId the review id
     * @param userId   the user id
     * @return true when user already has review
     * @throws DaoException the dao exception
     */
    boolean isUserHasReview(Long reviewId, Long userId) throws DaoException;

}
