package com.epam.jwd.training.service.impl;

import com.epam.jwd.training.dao.ReviewDao;
import com.epam.jwd.training.dao.impl.ReviewDaoImpl;
import com.epam.jwd.training.entity.Review;
import com.epam.jwd.training.exception.DaoException;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.service.ReviewService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class ReviewServiceImpl implements ReviewService {

    private static final Logger LOGGER = LogManager.getLogger(ReviewServiceImpl.class);

    private final ReviewDao reviewDao = ReviewDaoImpl.INSTANCE;

    @Override
    public List<Review> findAll() throws ServiceException {
        List<Review> reviews;
        try {
            reviews = reviewDao.findAll();
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return reviews;
    }

    @Override
    public Optional<Review> findById(long id) throws ServiceException {
        Optional<Review> reviewOptional;
        try {
            reviewOptional = reviewDao.findById(id);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return reviewOptional;
    }

    @Override
    public boolean delete(long id) throws ServiceException {
        boolean isDeleted;
        try {
            isDeleted = reviewDao.delete(id);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isDeleted;
    }

    @Override
    public boolean save(Review review) throws ServiceException {
        boolean isSaved;
        try {
            isSaved = reviewDao.save(review);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isSaved;
    }

    @Override
    public Optional<Review> findReviewByUserId(long id) throws ServiceException {
        Optional<Review> reviewOptional;
        try {
            reviewOptional = reviewDao.findReviewByUserId(id);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return reviewOptional;
    }

}
