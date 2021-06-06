package com.epam.jwd.training.dao.impl;

import com.epam.jwd.training.dao.ColumnName;
import com.epam.jwd.training.dao.TaskDao;
import com.epam.jwd.training.entity.Course;
import com.epam.jwd.training.entity.Task;
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

public class TaskDaoImpl implements TaskDao {

    public static final TaskDaoImpl INSTANCE = new TaskDaoImpl();

    private static final Logger LOGGER = LogManager.getLogger(TaskDaoImpl.class);

    private static final String FIND_ALL_TASKS_SQL = "SELECT task_id, task_description, course_id, course_name  " +
            "FROM training.tasks " +
            "INNER JOIN training.courses " +
            "ON course_id = c_id";
    private static final String FIND_TASK_BY_ID_SQL = FIND_ALL_TASKS_SQL +
            " WHERE task_id = ?";
    private static final String FIND_ALL_TASKS_BY_COURSE_ID_SQL = FIND_ALL_TASKS_SQL +
            " WHERE course_id = ?";
    private static final String UPDATE_TASK_SQL = "UPDATE training.tasks SET task_description = ? " +
            "WHERE task_id = ?";
    private static final String ADD_TASK_SQL = "INSERT INTO training.tasks (task_description, course_id) " +
            "VALUES (?, ?)";
    private static final String DELETE_TASK_SQL = "DELETE FROM training.tasks " +
            "WHERE task_id = ?";

    private TaskDaoImpl() {
    }

    @Override
    public List<Task> findAllTasksByCourseId(long id) throws DaoException {
        List<Task> tasks = new ArrayList<>();
        try (Connection connection = ConcurrentConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_TASKS_BY_COURSE_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Task task = buildTask(resultSet);
                tasks.add(task);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
        return tasks;
    }

    @Override
    public boolean update(Task task) throws DaoException {
        boolean isUpdate;
        try (Connection connection = ConcurrentConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TASK_SQL)) {
            preparedStatement.setString(1, task.getDescription());
            preparedStatement.setLong(2, task.getId());

            isUpdate = preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean save(Task task) throws DaoException {
        boolean isSaved;
        try (Connection connection = ConcurrentConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_TASK_SQL)) {
            preparedStatement.setString(1, task.getDescription());
            preparedStatement.setLong(2, task.getCourse().getId());

            isSaved = preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
        return isSaved;
    }

    @Override
    public List<Task> findAll() throws DaoException {
        List<Task> tasks = new ArrayList<>();
        try (Connection connection = ConcurrentConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_TASKS_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Task task = buildTask(resultSet);
                tasks.add(task);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
        return tasks;
    }

    @Override
    public Optional<Task> findById(long id) throws DaoException {
        Optional<Task> taskOptional = Optional.empty();
        try (Connection connection = ConcurrentConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_TASK_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Task task = buildTask(resultSet);
                taskOptional = Optional.of(task);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
        return taskOptional;
    }

    @Override
    public boolean delete(long id) throws DaoException {
        boolean isDeleted;
        try (Connection connection = ConcurrentConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TASK_SQL)) {
            preparedStatement.setLong(1, id);

            isDeleted = preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
        return isDeleted;
    }

    private Task buildTask(ResultSet resultSet) throws SQLException {
        Course course = Course.builder()
                .setId(resultSet.getLong(ColumnName.COURSE_ID))
                .setName(resultSet.getString(ColumnName.COURSE_NAME))
                .build();
        return Task.builder()
                .setId(resultSet.getLong(ColumnName.TASK_ID))
                .setDescription(resultSet.getString(ColumnName.TASK_DESCRIPTION))
                .setCourse(course)
                .build();
    }

}
