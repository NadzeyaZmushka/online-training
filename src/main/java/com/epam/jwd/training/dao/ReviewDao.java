package com.epam.jwd.training.dao;

import com.epam.jwd.training.entity.Review;

import java.util.List;

public interface ReviewDao extends BaseDao<Review> {

    List<Review> findAllReviewsByStudentId(long id);

    List<Review> findAllReviewsByTeacherId(long id);

    List<Review> findAllReviewsByCourseId(long id);

}
