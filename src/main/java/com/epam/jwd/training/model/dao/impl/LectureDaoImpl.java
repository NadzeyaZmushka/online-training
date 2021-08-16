package com.epam.jwd.training.model.dao.impl;

import com.epam.jwd.training.model.dao.ColumnName;
import com.epam.jwd.training.model.dao.LectureDao;
import com.epam.jwd.training.model.entity.Course;
import com.epam.jwd.training.model.entity.Lecture;
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

public class LectureDaoImpl implements LectureDao {

    public static final LectureDaoImpl INSTANCE = new LectureDaoImpl();

    private static final Logger LOGGER = LogManager.getLogger(LectureDaoImpl.class);

    private static final String FIND_ALL_TASKS_SQL = "SELECT l_id, lecture_name, course_id, course_name  " +
            "FROM training.lectures " +
            "INNER JOIN training.courses " +
            "ON course_id = c_id";
    private static final String FIND_TASK_BY_ID_SQL = FIND_ALL_TASKS_SQL +
            " WHERE l_id = ?";
    private static final String FIND_ALL_TASKS_BY_COURSE_ID_SQL = FIND_ALL_TASKS_SQL +
            " WHERE course_id = ?";
    private static final String UPDATE_TASK_SQL = "UPDATE training.lectures SET lecture_name = ? " +
            "WHERE l_id = ?";
    private static final String ADD_LECTURE_SQL = "INSERT INTO training.lectures (lecture_name, course_id) " +
            "VALUES (?, ?)";
    private static final String DELETE_TASK_SQL = "DELETE FROM training.lectures " +
            "WHERE l_id = ?";
    private static final String FIND_LECTURE_BY_ID_AND_COURSE_ID = "SELECT l_id, lecture_name, course_id, course_name FROM training.lectures INNER JOIN training.courses ON course_id = c_id WHERE l_id = ? AND course_id = ?";

    private final ConnectionPool connectionPool = ConcurrentConnectionPool.getInstance();


    private LectureDaoImpl() {
    }

    @Override
    public List<Lecture> findAllLecturesByCourseId(Long courseId) throws DaoException {
        List<Lecture> lectures = new ArrayList<>();
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_TASKS_BY_COURSE_ID_SQL)) {
            preparedStatement.setLong(1, courseId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Lecture lecture = buildLecture(resultSet);
                lectures.add(lecture);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
        return lectures;
    }

    @Override
    public boolean update(Lecture lecture) throws DaoException {
        boolean isUpdate;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TASK_SQL)) {
            preparedStatement.setString(1, lecture.getName());
            preparedStatement.setLong(2, lecture.getId());

            isUpdate = preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean addLecture(Lecture lecture) throws DaoException {
        boolean isSaved;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_LECTURE_SQL)) {
            preparedStatement.setString(1, lecture.getName());
            preparedStatement.setLong(2, lecture.getCourse().getId());

            isSaved = preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
        return isSaved;
    }

    @Override
    public Optional<Lecture> findLectureByIdAndCourseId(Long lectureId, Long courseId) throws DaoException {
        Optional<Lecture> lectureOptional = Optional.empty();

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_LECTURE_BY_ID_AND_COURSE_ID)) {
            preparedStatement.setLong(1, lectureId);
            preparedStatement.setLong(2, courseId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Lecture lecture = buildLecture(resultSet);
                lectureOptional = Optional.of(lecture);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
        return lectureOptional;
    }

    @Override
    public List<Lecture> findAll() throws DaoException {
        List<Lecture> lectures = new ArrayList<>();
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_TASKS_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Lecture lecture = buildLecture(resultSet);
                lectures.add(lecture);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
        return lectures;
    }

    @Override
    public Optional<Lecture> findById(Long id) throws DaoException {
        Optional<Lecture> taskOptional = Optional.empty();
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_TASK_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Lecture lecture = buildLecture(resultSet);
                taskOptional = Optional.of(lecture);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
        return taskOptional;
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        boolean isDeleted;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TASK_SQL)) {
            preparedStatement.setLong(1, id);

            isDeleted = preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
        return isDeleted;
    }

    public static LectureDaoImpl getInstance() {
        return INSTANCE;
    }

    private Lecture buildLecture(ResultSet resultSet) throws SQLException {
        Course course = Course.builder()
                .setId(resultSet.getLong(ColumnName.COURSE_ID))
                .setName(resultSet.getString(ColumnName.COURSE_NAME))
                .build();
        return Lecture.builder()
                .setId(resultSet.getLong(ColumnName.L_ID))
                .setName(resultSet.getString(ColumnName.LECTURE_NAME))
                .setCourse(course)
                .build();
    }

}
