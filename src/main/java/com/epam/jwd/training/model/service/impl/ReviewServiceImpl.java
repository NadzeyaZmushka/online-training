package com.epam.jwd.training.model.service.impl;

import com.epam.jwd.training.exception.DaoException;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.dao.ReviewDao;
import com.epam.jwd.training.model.dao.impl.ReviewDaoImpl;
import com.epam.jwd.training.model.entity.Review;
import com.epam.jwd.training.model.service.ReviewService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * Class review service
 *
 * @author Nadzeya Zmushka
 */
public class ReviewServiceImpl implements ReviewService {

    public static final ReviewServiceImpl INSTANCE = new ReviewServiceImpl();

    private static final Logger LOGGER = LogManager.getLogger(ReviewServiceImpl.class);

    private final ReviewDao reviewDao = ReviewDaoImpl.getInstance();

    private ReviewServiceImpl() {
    }

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
    public Optional<Review> findById(Long id) throws ServiceException {
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
    public boolean delete(Long id) throws ServiceException {
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
    public boolean addReview(Review review) throws ServiceException {
        boolean isSaved;
        try {
            isSaved = reviewDao.addReview(review);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isSaved;
    }

    @Override
    public Optional<Review> findReviewByUserId(Long id) throws ServiceException {
        Optional<Review> reviewOptional;
        try {
            reviewOptional = reviewDao.findReviewByUserId(id);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return reviewOptional;
    }

    @Override
    public boolean isUserHasReview(Long reviewId, Long userId) throws ServiceException {
        boolean isHas;
        try {
            isHas = reviewDao.isUserHasReview(reviewId, userId);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isHas;
    }

    public static ReviewServiceImpl getInstance() {
        return INSTANCE;
    }

}
