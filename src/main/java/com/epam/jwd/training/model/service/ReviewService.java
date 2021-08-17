package com.epam.jwd.training.model.service;

import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.entity.Review;

import java.util.List;
import java.util.Optional;

/**
 * The interface review service
 *
 * @author Nadzeya Zmushka
 */
public interface ReviewService {

    /**
     * Find all reviews
     *
     * @return List of reviews
     * @throws ServiceException the service exception
     */
    List<Review> findAll() throws ServiceException;

    /**
     * @param id the review id
     * @return Optional<Review> with such id
     * @throws ServiceException the service exception
     */
    Optional<Review> findById(Long id) throws ServiceException;

    /**
     * Delete review
     *
     * @param id the review id
     * @return true if the action was successful
     * @throws ServiceException the service exception
     */
    boolean delete(Long id) throws ServiceException;

    /**
     * Add new review
     *
     * @param review {@link Review}
     * @return true if the action was successful
     * @throws ServiceException the service exception
     */
    boolean addReview(Review review) throws ServiceException;

    /**
     * Find review from user
     *
     * @param id the user id
     * @return Optional<Review>
     * @throws ServiceException the service exception
     */
    Optional<Review> findReviewByUserId(Long id) throws ServiceException;

    /**
     * User has review
     *
     * @param reviewId the review id
     * @param userId   the user id
     * @return true if the action was successful
     * @throws ServiceException the service exception
     */
    boolean isUserHasReview(Long reviewId, Long userId) throws ServiceException;

}
