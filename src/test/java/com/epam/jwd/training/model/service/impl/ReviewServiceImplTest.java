package com.epam.jwd.training.model.service.impl;

import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.entity.Review;
import com.epam.jwd.training.model.entity.User;
import com.epam.jwd.training.model.service.ReviewService;
import com.epam.jwd.training.model.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class ReviewServiceImplTest {

    private final ReviewService reviewServiceTest = ReviewServiceImpl.getInstance();
    private final UserService userService = UserServiceImpl.getInstance();

    private final Review expected = Review.builder()
            .setDescription("description")
            .setDate(Date.valueOf(LocalDate.now()))
            .build();

    @Before
    public void saveReview() throws ServiceException {
        User user = userService.findById(14L).get();
        expected.setUser(user);
        reviewServiceTest.save(expected);
    }

    @After
    public void DeleteReview() throws ServiceException {
        reviewServiceTest.delete(expected.getId());
    }

    @Test
    public void findReviewByUserId() throws ServiceException {

        Optional<Review> actual = reviewServiceTest.findReviewByUserId(14L);

        assertEquals(Optional.of(expected), actual);
    }

}