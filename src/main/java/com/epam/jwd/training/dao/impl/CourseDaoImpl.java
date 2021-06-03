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
            "start_course, end_course, cost_course, teacher_id, teacher_name, teacher_surname" +
            " FROM training.course INNER JOIN training.teacher ON teacher_id=t_id";
    //   private static final String FIND_COURSES_BY_TEACHER_ID = "SELECT c_id, course_name, c_description, start_course, end_course, cost_course FROM training.course WHERE teacher_id = ?";
    private static final String FIND_COURSE_BY_ID_SQL = "SELECT c_id, course_name, c_description, start_course, end_course, cost_course, teacher_id, teacher_name, teacher_surname FROM training.course INNER JOIN training.teacher ON teacher_id=t_id WHERE c_id = ?";
    private static final String ADD_COURSE_SQL = "INSERT INTO course (course_name, c_description, start_course, end_course, cost_course, teacher_id) VALUES (?, ?, ?, ?, ?, ?)"; //???
    private static final String DELETE_COURSE_SQL = "DELETE FROM course WHERE c_id = ?";

//    @Override
//    public List<Course> findAllCoursesByTeacherId(long id) {
//        List<Course> courses = new ArrayList<>();
//        try (Connection connection = ConcurrentConnectionPool.getInstance().takeConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(FIND_COURSES_BY_TEACHER_ID)) {
//            preparedStatement.setLong(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                Teacher teacher = Teacher.builder()
//                        .setId(resultSet.getLong(ColumnName.ID_TEACHER))
//                        .setName(resultSet.getString(ColumnName.TEACHER_NAME))
//                        .setSurname(resultSet.getString(ColumnName.TEACHER_SURNAME))
//                        .build();
//                Course course = Course.builder()
//                        .setId(resultSet.getLong(ColumnName.ID_COURSE))
//                        .setName(resultSet.getString(ColumnName.COURSE_NAME))
//                        .setDescription(resultSet.getString(ColumnName.COURSE_DESCRIPTION))
//                        .setStartCourse(resultSet.getDate(ColumnName.START_COURSE))
//                        .setEndCourse(resultSet.getDate(ColumnName.END_COURSE))
//                        .setCost(resultSet.getBigDecimal(ColumnName.COST_COURSE))
//                        .setTeacher(teacher)
//                        .build();
//                courses.add(course);
//            }
//        } catch (SQLException e) {
//            LOGGER.error(e.getMessage());
//            throw new DaoException(e);
//        }
//        return courses;
//    }

//    @Override
//    public Course updateCourseName(Course course) {
//        return null;
//    }
//
//    @Override
//    public Course updateDescription(Course course) {
//        return null;
//    }
//
//    @Override
//    public Course updateCost(Course course) {
//        return null;
//    }
//
//    @Override
//    public Course updateTeacher(Course course) {
//        return null;
//    }

    @Override
    public List<Course> findAll() {
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
        }
        return courses;
    }

    @Override
    public Optional<Course> findById(long id) {
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
        }
        return courseOptional;
    }

    @Override
    public Course save(Course course) {
        try (Connection connection = ConcurrentConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_COURSE_SQL)) {
            preparedStatement.setString(1, course.getName());
            preparedStatement.setString(2, course.getDescription());
            preparedStatement.setDate(3, course.getStartCourse());
            preparedStatement.setDate(4, course.getEndCourse());
            preparedStatement.setBigDecimal(5, course.getCost());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                course.setId(generatedKeys.getLong(ColumnName.ID_COURSE));
            }
            return course;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(long id) {
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
                .setSurname(ColumnName.TEACHER_SURNAME)
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
