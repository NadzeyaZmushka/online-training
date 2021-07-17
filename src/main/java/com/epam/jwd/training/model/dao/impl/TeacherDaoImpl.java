package com.epam.jwd.training.model.dao.impl;

import com.epam.jwd.training.model.dao.ColumnName;
import com.epam.jwd.training.model.dao.TeacherDao;
import com.epam.jwd.training.model.entity.Teacher;
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

public class TeacherDaoImpl implements TeacherDao {

    public static final TeacherDaoImpl INSTANCE = new TeacherDaoImpl();

    private static final Logger LOGGER = LogManager.getLogger(TeacherDaoImpl.class);

    private static final String FIND_ALL_TEACHERS_SQL = "SELECT t_id, teacher_name, teacher_surname " +
            "FROM training.teachers";
    private static final String FIND_TEACHER_BY_SURNAME_SQL = FIND_ALL_TEACHERS_SQL + " WHERE teacher_surname = ?";
    private static final String FIND_TEACHER_BY_ID_SQL = FIND_ALL_TEACHERS_SQL + " WHERE t_id = ?";
    private static final String ADD_TEACHER_SQL = "INSERT INTO training.teachers (teacher_name, teacher_surname) " +
            "VALUES (?,?)";
    private static final String DELETE_TEACHER_SQL = "DELETE FROM training.teachers " +
            "WHERE t_id = ?";

    private final ConnectionPool connectionPool = ConcurrentConnectionPool.getInstance();

    private TeacherDaoImpl() {
    }

    @Override
    public Optional<Teacher> findBySurname(String surname) throws DaoException {
        Optional<Teacher> teacherOptional = Optional.empty();
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_TEACHER_BY_SURNAME_SQL)) {
            preparedStatement.setString(1, surname);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Teacher teacher = Teacher.builder()
                        .setId(resultSet.getLong(ColumnName.T_ID))
                        .setName(resultSet.getString(ColumnName.TEACHER_NAME))
                        .setSurname(resultSet.getString(ColumnName.TEACHER_SURNAME))
                        .build();
                teacherOptional = Optional.of(teacher);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
        return teacherOptional;
    }

    @Override
    public List<Teacher> findAll() throws DaoException {
        List<Teacher> teachers = new ArrayList<>();
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_TEACHERS_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Teacher teacher = Teacher.builder()
                        .setId(resultSet.getLong(ColumnName.T_ID))
                        .setName(resultSet.getString(ColumnName.TEACHER_NAME))
                        .setSurname(resultSet.getString(ColumnName.TEACHER_SURNAME))
                        .build();
                teachers.add(teacher);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException(e);
        }
        return teachers;

    }

    @Override
    public Optional<Teacher> findById(Long id) throws DaoException {
        Optional<Teacher> teacherOptional = Optional.empty();
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_TEACHER_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Teacher teacher = Teacher.builder()
                        .setId(resultSet.getLong(ColumnName.T_ID))
                        .setName(resultSet.getString(ColumnName.TEACHER_NAME))
                        .setSurname(resultSet.getString(ColumnName.TEACHER_SURNAME))
                        .build();
                teacherOptional = Optional.of(teacher);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException(e);
        }
        return teacherOptional;

    }

    @Override
    public boolean save(Teacher teacher) throws DaoException {
        boolean isSaved;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_TEACHER_SQL)) {
            preparedStatement.setString(1, teacher.getName());
            preparedStatement.setString(2, teacher.getSurname());

            isSaved = preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException(e);
        }
        return isSaved;
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        boolean isDeleted;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TEACHER_SQL)) {
            preparedStatement.setLong(1, id);

            isDeleted = preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException(e);
        }
        return isDeleted;
    }

    public static TeacherDaoImpl getInstance() {
        return INSTANCE;
    }

}