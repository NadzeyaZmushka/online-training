package com.epam.jwd.training.dao.impl;

import com.epam.jwd.training.dao.ColumnName;
import com.epam.jwd.training.dao.UserDao;
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

public class UserDaoImpl implements UserDao {

    // todo: singleton

    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);

    private static final String FIND_ALL_USERS = "SELECT u_id, user_name, user_surname, user_email, user_role " +
            "FROM training.users";
    private static final String FIND_USER_BY_ID = "SELECT u_id, user_name, user_surname, user_email, user_role " +
            "FROM training.users " +
            "WHERE u_id = ?";
    private static final String FIND_ALL_USERS_ON_COURSE_SQL = "SELECT u_id, user_name, user_surname, user_email, user_role " +
            "FROM training.users_x_courses " +
            "INNER JOIN training.users on id_user = u_id " +
            "INNER JOIN training.courses on id_course = c_id " +
            "WHERE c_id = ?";
    private static final String ADD_USER_SQL = "INSERT INTO training.users (user_name, user_surname, user_email, password, user_role) " +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE_USER_SQL = "DELETE FROM training.users " +
            "WHERE u_id = ?";
    private static final String UPDATE_USER_ROLE = "UPDATE training.users SET user_role = ? " +
            "WHERE u_id = ?";
    private static final String UPDATE_USER_PASSWORD = "UPDATE training.users SET password = ? " +
            "WHERE u_id = ?";
    private static final String FIND_USER_BY_EMAIL = "SELECT u_id, user_name, user_surname, user_email, user_role " +
            "FROM training.users " +
            "WHERE user_email = ?";

    @Override
    public List<User> findAllUsersOnCourse(long id) throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConcurrentConnectionPool.getInstance().takeConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_USERS_ON_COURSE_SQL);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = User.builder()
                        .setId(resultSet.getLong(ColumnName.ID_USER))
                        .setName(resultSet.getString(ColumnName.USER_NAME))
                        .setSurname(resultSet.getString(ColumnName.USER_SURNAME))
                        .setEmail(resultSet.getString(ColumnName.USER_EMAIL))
                        .setRole(RoleType.valueOf(resultSet.getString(ColumnName.ROLE).toUpperCase()))
                        .build();
                users.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
        return users;
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConcurrentConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_USERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = User.builder()
                        .setId(resultSet.getLong(ColumnName.ID_USER))
                        .setName(resultSet.getString(ColumnName.USER_NAME))
                        .setSurname(resultSet.getString(ColumnName.USER_SURNAME))
                        .setEmail(resultSet.getString(ColumnName.USER_EMAIL))
                        .setRole(RoleType.valueOf(resultSet.getString(ColumnName.ROLE).toUpperCase())).build();
                users.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
        return users;
    }

    @Override
    public Optional<User> findById(long id) {
        Optional<User> userOptional = Optional.empty();
        try (Connection connection = ConcurrentConnectionPool.getInstance().takeConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = User.builder()
                        .setId(resultSet.getLong(ColumnName.ID_USER))
                        .setName(resultSet.getString(ColumnName.USER_NAME))
                        .setSurname(resultSet.getString(ColumnName.USER_SURNAME))
                        .setEmail(resultSet.getString(ColumnName.USER_SURNAME))
                        .setRole(RoleType.valueOf(resultSet.getString(ColumnName.ROLE).toUpperCase()))
                        .build();
                userOptional = Optional.of(user);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return userOptional;
    }

    @Override
    public Optional<User> findByEmail(String email) throws DaoException {
        Optional<User> userOptional = Optional.empty();
        try (Connection connection = ConcurrentConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = User.builder()
                        .setId(resultSet.getLong(ColumnName.ID_USER))
                        .setName(resultSet.getString(ColumnName.USER_NAME))
                        .setSurname(resultSet.getString(ColumnName.USER_SURNAME))
                        .setEmail(resultSet.getString(ColumnName.USER_EMAIL))
                        .setRole(RoleType.valueOf(resultSet.getString(ColumnName.ROLE).toUpperCase()))
                        .build();
                userOptional = Optional.of(user);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
        return userOptional;
    }

    @Override
    public boolean addUser(User user, String password) throws DaoException {
        try (Connection connection = ConcurrentConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER_SQL)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, user.getRole().toString());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
    }

    // ???
    @Override
    public boolean updateUserToAdmin(long userId) throws DaoException {
        try (Connection connection = ConcurrentConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_ROLE)) {
            preparedStatement.setString(1, String.valueOf(RoleType.ADMIN));
            preparedStatement.setLong(2, userId);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
    }

    @Override
    public boolean updatePassword(String password, long userId) throws DaoException {
        try (Connection connection = ConcurrentConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_PASSWORD)) {
            preparedStatement.setString(1, password);
            preparedStatement.setLong(2, userId);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(long id) throws DaoException {
        try (Connection connection = ConcurrentConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_SQL)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
    }

}
