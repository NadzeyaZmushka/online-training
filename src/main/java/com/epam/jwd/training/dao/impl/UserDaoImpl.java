package com.epam.jwd.training.dao.impl;

import com.epam.jwd.training.dao.UserDao;
import com.epam.jwd.training.entity.RoleType;
import com.epam.jwd.training.entity.User;
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

    //    private static final String FIND_ALL_USERS = "SELECT id"
    private static final String FIND_USER_BY_COURSE_ID = "SELECT id, user_name, user_surname, user_email, user_role FROM training.users WHERE course_id = ?";
    private static final String FIND_USER_BY_ID = "SELECT id, user_name, user_surname, user_email, user_role, course_id FROM training.users WHERE id = ?";


    @Override
    public List<User> findAllUsersByCourseId(long id) {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConcurrentConnectionPool.getInstance().takeConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_COURSE_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = User.builder()
                        .setId(resultSet.getLong(1))
                        .setName(resultSet.getString(2))
                        .setSurname(resultSet.getString(3))
                        .setEmail(resultSet.getString(4))
                        .setRole(RoleType.valueOf(resultSet.getString(6).toUpperCase()))
                        .build();
                users.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return users;
    }

    @Override
    public List<User> findAll() {
        return null;
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
                        .setId(resultSet.getLong(1))
                        .setName(resultSet.getString(2))
                        .setSurname(resultSet.getString(3))
                        .setEmail(resultSet.getString(4))
                        .setRole(RoleType.valueOf(resultSet.getString(6).toUpperCase()))
                        .build();
                userOptional = Optional.of(user);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return userOptional;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }
}
