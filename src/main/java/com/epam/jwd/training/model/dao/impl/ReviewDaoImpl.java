package com.epam.jwd.training.model.dao.impl;

import com.epam.jwd.training.model.dao.ColumnName;
import com.epam.jwd.training.model.dao.ReviewDao;
import com.epam.jwd.training.model.entity.Review;
import com.epam.jwd.training.model.entity.RoleType;
import com.epam.jwd.training.model.entity.User;
import com.epam.jwd.training.exception.DaoException;
import com.epam.jwd.training.pool.ConcurrentConnectionPool;
import com.epam.jwd.training.pool.ConnectionPool;
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

    public static final ReviewDaoImpl INSTANCE = new ReviewDaoImpl();

    private static final Logger LOGGER = LogManager.getLogger(ReviewDaoImpl.class);

    private static final String FIND_ALL_REVIEWS_SQL = "SELECT r_id, r_description, date_review, user_id, user_name, user_surname, user_email, role, enabled " +
            "FROM training.reviews " +
            "INNER JOIN users ON user_id = u_id " +
            "ORDER BY date_review";
    private static final String ADD_REVIEW_SQL = "INSERT INTO training.reviews (r_description, date_review, user_id) " +
            "VALUES (?, ?, ?)";
    private static final String DELETE_REVIEW_SQL = "DELETE FROM training.reviews " +
            "WHERE r_id = ?";
    private static final String FIND_REVIEW_BY_USER_ID_SQL = "SELECT r_id, r_description, date_review " +
            "FROM training.reviews " +
            "WHERE user_id = ?";

    private final ConnectionPool connectionPool = ConcurrentConnectionPool.getInstance();

    private ReviewDaoImpl() {
    }

    @Override
    public boolean save(Review review) throws DaoException {
        boolean isSaved;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_REVIEW_SQL)) {
            preparedStatement.setString(1, review.getDescription());
            preparedStatement.setDate(2, review.getDate());
            preparedStatement.setLong(3, review.getUser().getId());

            isSaved = preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
        return isSaved;
    }

    @Override
    public Optional<Review> findReviewByUserId(Long userId) throws DaoException {
        Optional<Review> reviewOptional = Optional.empty();
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_REVIEW_BY_USER_ID_SQL)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Review review = Review.builder()
                        .setId(resultSet.getLong(ColumnName.REVIEW_ID))
                        .setDescription(resultSet.getString(ColumnName.REVIEW_DESCRIPTION))
                        .setDate(resultSet.getDate(ColumnName.DATE_REVIEW))
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
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_REVIEWS_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = User.builder()
                        .setId(resultSet.getLong(ColumnName.USER_ID))
                        .setName(resultSet.getString(ColumnName.USER_NAME))
                        .setSurname(resultSet.getString(ColumnName.USER_SURNAME))
                        .setEmail(resultSet.getString(ColumnName.USER_EMAIL))
                        .setRole(RoleType.resolvedById(resultSet.getLong(ColumnName.ROLE)))
                        .setEnabled(resultSet.getBoolean(ColumnName.USER_ENABLED))
                        .build();
                Review review = Review.builder()
                        .setId(resultSet.getLong(ColumnName.REVIEW_ID))
                        .setDescription(resultSet.getString(ColumnName.REVIEW_DESCRIPTION))
                        .setDate(resultSet.getDate(ColumnName.DATE_REVIEW))
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

    //??
    @Override
    public Optional<Review> findById(Long id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        boolean isDeleted;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_REVIEW_SQL)) {
            preparedStatement.setLong(1, id);

            isDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
        return isDeleted;
    }

    public static ReviewDaoImpl getInstance() {
        return INSTANCE;
    }

}
