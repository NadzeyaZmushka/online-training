package com.epam.jwd.training.model.service;

import com.epam.jwd.training.model.entity.Review;
import com.epam.jwd.training.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    List<Review> findAll() throws ServiceException;

    Optional<Review> findById(Long id) throws ServiceException;

    boolean delete(Long id) throws ServiceException;

    boolean save(Review review) throws ServiceException;

    Optional<Review> findReviewByUserId(Long id) throws ServiceException;

    boolean isUserHasReview(Long reviewId, Long userId) throws ServiceException;
}
