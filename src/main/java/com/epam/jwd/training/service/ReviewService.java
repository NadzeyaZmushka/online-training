package com.epam.jwd.training.service;

import com.epam.jwd.training.entity.Review;
import com.epam.jwd.training.exception.DaoException;
import com.epam.jwd.training.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    List<Review> findAll() throws ServiceException;

    Optional<Review> findById(long id) throws ServiceException;

    boolean delete(long id) throws ServiceException;

    public boolean save(Review review) throws ServiceException;

    Optional<Review> findReviewByUserId(long id) throws ServiceException;

}
