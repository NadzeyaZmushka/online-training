package com.epam.jwd.training.dao.impl;

import com.epam.jwd.training.dao.ColumnName;
import com.epam.jwd.training.dao.CourseDao;
import com.epam.jwd.training.entity.Course;
import com.epam.jwd.training.entity.Teacher;
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

public class CourseDaoImpl implements CourseDao {
    // todo: singleton
    private static final Logger LOGGER = LogManager.getLogger(CourseDaoImpl.class);

    private static final String FIND_ALL_COURSES_SQL = "SELECT c_id, course_name, c_description, " +
            "start_course, end_course, cost_course, teacher_id, teacher_name, teacher_surname " +
            "FROM training.courses " +
            "INNER JOIN training.teachers " +
            "ON teacher_id=t_id";
    private static final String FIND_COURSES_BY_TEACHER_ID_SQL = "SELECT c_id, course_name, c_description, start_course, " +
            "end_course, cost_course, teacher_id, teacher_name, teacher_surname " +
            "FROM training.courses " +
            "INNER JOIN training.teachers " +
            "ON teacher_id=t_id " +
            "WHERE t_id = ?";
    private static final String FIND_COURSE_BY_ID_SQL = "SELECT c_id, course_name, c_description, start_course, end_course, cost_course, teacher_id, teacher_name, teacher_surname " +
            "FROM training.courses " +
            "INNER JOIN training.teachers " +
            "ON teacher_id=t_id " +
            "WHERE c_id = ?";
    private static final String ADD_COURSE_SQL = "INSERT INTO courses (course_name, c_description, start_course, end_course, cost_course) " +
            "VALUES (?, ?, ?, ?, ?)"; //???
    private static final String DELETE_COURSE_SQL = "DELETE FROM courses WHERE c_id = ?";
    private static final String UPDATE_COURSE_COST_SQL = "UPDATE training.courses " +
            "SET cost_course = ? " +
            "WHERE c_id = ?";
    private static final String UPDATE_START_END_COURSE_SQL = "UPDATE training.courses " +
            "SET start_course = ?, end_course = ? " +
            "WHERE c_id = ?";

    @Override
    public List<Course> findAllCoursesByTeacherId(long id) throws DaoException {
        List<Course> courses = new ArrayList<>();
        try (Connection connection = ConcurrentConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_COURSES_BY_TEACHER_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Course course = buildCourse(resultSet);
                courses.add(course);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException(e);
        }
        return courses;
    }

    @Override
    public boolean updateCost(Course course) throws DaoException {
        try (Connection connection = ConcurrentConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COURSE_COST_SQL)) {
            preparedStatement.setBigDecimal(1, course.getCost());
            preparedStatement.setLong(2, course.getId());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
    }

    @Override
    public boolean updateDate(Course course, long id) throws DaoException {
        try (Connection connection = ConcurrentConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_START_END_COURSE_SQL)) {
            preparedStatement.setDate(1, course.getStartCourse());
            preparedStatement.setDate(2, course.getEndCourse());
            preparedStatement.setLong(3, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<Course> findAll() throws DaoException {
        List<Course> courses = new ArrayList<>();
        try (Connection connection = ConcurrentConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_COURSES_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Course course = buildCourse(resultSet);
                courses.add(course);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException(e);
        }
        return courses;
    }

    @Override
    public Optional<Course> findById(long id) throws DaoException {
        Optional<Course> courseOptional = Optional.empty();
        try (Connection connection = ConcurrentConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_COURSE_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Course course = buildCourse(resultSet);
                courseOptional = Optional.of(course);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException(e);
        }
        return courseOptional;
    }

    @Override
    public boolean save(Course course) throws DaoException {
        try (Connection connection = ConcurrentConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_COURSE_SQL)) {
            preparedStatement.setString(1, course.getName());
            preparedStatement.setString(2, course.getDescription());
            preparedStatement.setDate(3, course.getStartCourse());
            preparedStatement.setDate(4, course.getEndCourse());
            preparedStatement.setBigDecimal(5, course.getCost());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(long id) throws DaoException {
        try (Connection connection = ConcurrentConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COURSE_SQL)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException(e);
        }
    }

    private Course buildCourse(ResultSet resultSet) throws SQLException {
        Teacher teacher = Teacher.builder()
                .setId(resultSet.getLong(ColumnName.TEACHER_ID))
                .setName(resultSet.getString(ColumnName.TEACHER_NAME))
                .setSurname(resultSet.getString(ColumnName.TEACHER_SURNAME))
                .build();
        return Course.builder().setId(resultSet.getLong(ColumnName.ID_COURSE))
                .setName(resultSet.getString(ColumnName.COURSE_NAME))
                .setDescription(resultSet.getString(ColumnName.COURSE_DESCRIPTION))
                .setStartCourse(resultSet.getDate(ColumnName.START_COURSE))
                .setEndCourse(resultSet.getDate(ColumnName.END_COURSE))
                .setCost(resultSet.getBigDecimal(ColumnName.COST_COURSE))
                .setTeacher(teacher)
                .build();
    }

}
