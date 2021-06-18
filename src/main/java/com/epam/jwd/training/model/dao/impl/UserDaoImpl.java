package com.epam.jwd.training.model.dao.impl;

import com.epam.jwd.training.model.dao.ColumnName;
import com.epam.jwd.training.model.dao.UserDao;
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

public class UserDaoImpl implements UserDao {

    public static final UserDaoImpl INSTANCE = new UserDaoImpl();

    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);

    private static final String FIND_ALL_USERS_SQL = "SELECT u_id, user_name, user_surname, user_email, role, enabled " +
            "FROM training.users";
    private static final String FIND_USER_BY_ID_SQL = FIND_ALL_USERS_SQL + " WHERE u_id = ?";
    private static final String FIND_USER_BY_EMAIL_SQL = FIND_ALL_USERS_SQL + " WHERE user_email = ?";
    private static final String FIND_ALL_USERS_ON_COURSE_SQL = "SELECT u_id, user_name, user_surname, user_email, role, enabled " +
            "FROM training.users_x_courses " +
            "INNER JOIN training.users on user_id = u_id " +
            "INNER JOIN training.courses on course_id = c_id " +
            "WHERE course_id = ?";
    private static final String ENROLL_USER_ON_COURSE_SQL = "INSERT INTO training.users_x_courses (user_id, course_id) " +
            "VALUES (?, ?)";
    private static final String UPDATE_NAME_AND_SURNAME_SQL = "UPDATE training.users " +
            "SET user_name = ?, user_surnsme = ? " +
            "WHERE u_id = ?";
    private static final String ADD_USER_SQL = "INSERT INTO training.users (user_name, user_surname, user_email, password, role, enabled) " +
            "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE_USER_SQL = "DELETE FROM training.users " +
            "WHERE u_id = ?";
    private static final String UPDATE_USER_ROLE_SQL = "UPDATE training.users SET role = ? " +
            "WHERE u_id = ?";
    private static final String UPDATE_USER_PASSWORD_SQL = "UPDATE training.users SET password = ? " +
            "WHERE u_id = ?";
    private static final String BLOCK_USER_SQL = "UPDATE training.users " +
            "SET enabled = false WHERE u_id = ?";
    private static final String UNBLOCK_USER_SQL = "UPDATE training.users " +
            "SET enabled = true WHERE u_id = ?";

    private final ConnectionPool connectionPool = ConcurrentConnectionPool.getInstance();

    private UserDaoImpl() {
    }

    @Override
    public List<User> findAllUsersOnCourse(Long courseId) throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection connection = connectionPool.takeConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_USERS_ON_COURSE_SQL);
            preparedStatement.setLong(1, courseId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = buildUser(resultSet);
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
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_USERS_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = buildUser(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
        return users;
    }

    @Override
    public Optional<User> findById(Long id) {
        Optional<User> userOptional = Optional.empty();
        try (Connection connection = connectionPool.takeConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID_SQL);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = buildUser(resultSet);
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
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL_SQL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = buildUser(resultSet);
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
        boolean isAdded;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER_SQL)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, password);
            preparedStatement.setLong(5, user.getRole().getId());
            preparedStatement.setBoolean(6, user.isEnabled());

            isAdded = preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
        return isAdded;
    }

    @Override
    public boolean enrollCourse(User user, Long courseId) throws DaoException {
        boolean isEnrolled;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ENROLL_USER_ON_COURSE_SQL)) {
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setLong(2, courseId);

            isEnrolled = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
        return isEnrolled;
    }

    // ???

    @Override
    public boolean updateUserToAdmin(User user) throws DaoException {
        boolean isUpdate;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_ROLE_SQL)) {
            preparedStatement.setLong(1, user.getRole().getId());
            preparedStatement.setLong(2, user.getId());

            isUpdate = preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean updatePassword(String password, Long userId) throws DaoException {
        boolean isUpdate;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_PASSWORD_SQL)) {
            preparedStatement.setString(1, password);
            preparedStatement.setLong(2, userId);

            isUpdate = preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean updateNameAndSurname(User user) throws DaoException {
        boolean isUpdate;
        try (Connection connection = connectionPool.takeConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_NAME_AND_SURNAME_SQL)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setLong(3, user.getId());

            isUpdate = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
        return isUpdate;
    }

    //??

    @Override
    public boolean blockUser(Long id) throws DaoException {
        boolean isBlocked;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(BLOCK_USER_SQL)) {
            preparedStatement.setLong(1, id);

            isBlocked = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
        return isBlocked;
    }
    //??

    @Override
    public boolean unblockUser(Long id) throws DaoException {
        boolean isUnblocked;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UNBLOCK_USER_SQL)) {
            preparedStatement.setLong(1, id);

            isUnblocked = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
        return isUnblocked;
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        boolean isDeleted;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_SQL)) {
            preparedStatement.setLong(1, id);

            isDeleted = preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
        return isDeleted;
    }

    public static UserDaoImpl getInstance() {
        return INSTANCE;
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        return User.builder()
                .setId(resultSet.getLong(ColumnName.U_ID))
                .setName(resultSet.getString(ColumnName.USER_NAME))
                .setSurname(resultSet.getString(ColumnName.USER_SURNAME))
                .setEmail(resultSet.getString(ColumnName.USER_EMAIL))
                .setRole(RoleType.resolvedById(resultSet.getLong(ColumnName.ROLE)))
                .setEnabled(resultSet.getBoolean(ColumnName.USER_ENABLED))
                .build();
    }

}
