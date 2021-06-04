package com.epam.jwd.training.dao.impl;

import com.epam.jwd.training.dao.ColumnName;
import com.epam.jwd.training.dao.ReviewDao;
import com.epam.jwd.training.entity.Review;
import com.epam.jwd.training.entity.RoleType;
import com.epam.jwd.training.entity.User;
import com.epam.jwd.training.exception.DaoException;
import com.epam.jwd.training.pool.ConcurrentConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReviewDaoImpl implements ReviewDao {

    private static final Logger LOGGER = LogManager.getLogger(ReviewDaoImpl.class);

    private static final String FIND_ALL_REVIEWS_SQL = "SELECT r_id, mark, r_description, user_id, user_name, user_surname, user_email, user_role " +
            "FROM training.reviews " +
            "INNER JOIN users ON user_id = u_id";
    private static final String FIND_REVIEWS_BY_ID_SQL = FIND_ALL_REVIEWS_SQL +
            " WHERE r_id = ?";
    private static final String ADD_REVIEW_SQL = "INSERT INTO training.reviews (mark, r_description, user_id) " +
            "VALUES (?, ?, ?)";
    private static final String DELETE_REVIEW_SQL = "DELETE FROM training.reviews " +
            "WHERE r_id = ?";
    private static final String FIND_REVIEW_BY_USER_ID_SQL = "SELECT r_id, mark, r_description " +
            "FROM training.reviews " +
            "WHERE user_id = ?";

    @Override
    public boolean save(Review review) throws DaoException {
        try (Connection connection = ConcurrentConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_REVIEW_SQL)) {
            preparedStatement.setInt(1, review.getMark());
            preparedStatement.setString(2, review.getDescription());
            preparedStatement.setLong(3, review.getUser().getId());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Review> findReviewByUserId(long id) throws DaoException {
        Optional<Review> reviewOptional = Optional.empty();
        try (Connection connection = ConcurrentConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_REVIEW_BY_USER_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Review review = Review.builder()
                        .setId(resultSet.getLong(ColumnName.REVIEW_ID))
                        .setMark(resultSet.getInt(ColumnName.MARK))
                        .setDescription(resultSet.getString(ColumnName.REVIEW_DESCRIPTION))
                        .build();
                reviewOptional = Optional.of(review);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
        return reviewOptional;
    }

    @Override
    public List<Review> findAll() throws DaoException {
        List<Review> reviews = new ArrayList<>();
        try (Connection connection = ConcurrentConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_REVIEWS_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = User.builder()
                        .setId(resultSet.getLong(ColumnName.USER_ID))
                        .setName(resultSet.getString(ColumnName.USER_NAME))
                        .setSurname(resultSet.getString(ColumnName.USER_SURNAME))
                        .setEmail(resultSet.getString(ColumnName.USER_EMAIL))
                        .setRole(RoleType.valueOf(resultSet.getString(ColumnName.ROLE).toUpperCase()))
                        .build();
                Review review = Review.builder()
                        .setId(resultSet.getLong(ColumnName.REVIEW_ID))
                        .setMark(resultSet.getInt(ColumnName.MARK))
                        .setDescription(resultSet.getString(ColumnName.REVIEW_DESCRIPTION))
                        .setUser(user)
                        .build();
                reviews.add(review);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
        return reviews;
    }

    @Override
    public Optional<Review> findById(long id) throws DaoException {
        Optional<Review> reviewOptional = Optional.empty();
        try (Connection connection = ConcurrentConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_REVIEWS_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = User.builder()
                        .setId(resultSet.getLong(ColumnName.USER_ID))
                        .setName(resultSet.getString(ColumnName.USER_NAME))
                        .setSurname(resultSet.getString(ColumnName.USER_SURNAME))
                        .setEmail(resultSet.getString(ColumnName.USER_EMAIL))
                        .setRole(RoleType.valueOf(resultSet.getString(ColumnName.ROLE).toUpperCase()))
                        .build();
                Review review = Review.builder()
                        .setId(resultSet.getLong(ColumnName.REVIEW_ID))
                        .setMark(resultSet.getInt(ColumnName.MARK))
                        .setDescription(resultSet.getString(ColumnName.REVIEW_DESCRIPTION))
                        .setUser(user)
                        .build();
                reviewOptional = Optional.of(review);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
        return reviewOptional;
    }

    @Override
    public boolean delete(long id) throws DaoException {
        try (Connection connection = ConcurrentConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_REVIEW_SQL)) {
            preparedStatement.setLong(1, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
    }

}
