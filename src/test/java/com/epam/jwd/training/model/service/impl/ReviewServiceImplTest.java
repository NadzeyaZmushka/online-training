package com.epam.jwd.training.model.service.impl;

import com.epam.jwd.training.exception.DaoException;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.dao.ReviewDao;
import com.epam.jwd.training.model.dao.impl.ReviewDaoImpl;
import com.epam.jwd.training.model.entity.Review;
import com.epam.jwd.training.model.service.ReviewService;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReviewServiceImplTest {

    private ReviewDao reviewDao;
    private ReviewService reviewService;
    private Review review1;
    private Review review2;
    private final List<Review> reviews = new ArrayList<>();

    @Before
    public void setUp() {
        reviewDao = mock(ReviewDaoImpl.class);
        reviewService = new ReviewServiceImpl(reviewDao);
        review1 = Review.builder()
                .setId(1L)
                .setDescription("Description")
                .setDate(Date.valueOf(LocalDate.now()))
                .build();
        review2 = Review.builder()
                .setId(2L)
                .setDescription("Description2")
                .setDate(Date.valueOf(LocalDate.now()))
                .build();
        reviews.add(review1);
    }

    @Test
    public void test_findAll() throws DaoException, ServiceException {
        when(reviewDao.findAll()).thenReturn(reviews);
        List<Review> actual = reviewService.findAll();
        assertEquals(reviews, actual);
    }

    @Test
    public void test_findById_returnOptionalOfReviewWithSuchId() throws DaoException, ServiceException {
        Optional<Review> expected = Optional.of(review1);
        when(reviewDao.findById(review1.getId())).thenReturn(expected);
        Optional<Review> actual = reviewService.findById(1L);
        assertEquals(expected, actual);
    }

    @Test
    public void test_deleteReview() throws DaoException, ServiceException {
        when(reviewDao.delete(anyLong())).thenReturn(true);
        boolean actual = reviewService.delete(1L);
        assertTrue(actual);
    }

    @Test
    public void addReview() throws DaoException, ServiceException {
        when(reviewDao.addReview(any(Review.class))).thenReturn(true);
        boolean actual = reviewService.addReview(review2);
        assertTrue(actual);
    }

    @Test
    public void findReviewByUserId() throws DaoException, ServiceException {
        Optional<Review> expected = Optional.of(review1);
        when(reviewDao.findReviewByUserId(anyLong())).thenReturn(expected);
        Optional<Review> actual = reviewService.findReviewByUserId(1L);
        assertEquals(expected, actual);
    }

    @Test
    public void isUserHasReview() throws DaoException, ServiceException {
        when(reviewDao.isUserHasReview(anyLong(), anyLong())).thenReturn(true);
        boolean actual = reviewService.isUserHasReview(1L, 2L);
        assertTrue(actual);
    }
}