package com.epam.jwd.training.model.dao.impl;

import com.epam.jwd.training.model.dao.ColumnName;
import com.epam.jwd.training.model.dao.CourseDao;
import com.epam.jwd.training.model.entity.Course;
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

public class CourseDaoImpl implements CourseDao {

    public static final CourseDaoImpl INSTANCE = new CourseDaoImpl();

    private static final Logger LOGGER = LogManager.getLogger(CourseDaoImpl.class);

    private static final String FIND_ALL_COURSES_SQL = "SELECT c_id, course_name, c_description, hours, " +
            "start_course, end_course, cost_course, teacher_id, teacher_name, teacher_surname " +
            "FROM training.courses " +
            "INNER JOIN training.teachers " +
            "ON teacher_id=t_id";
    private static final String FIND_COURSES_BY_TEACHER_ID_SQL = FIND_ALL_COURSES_SQL + " WHERE teacher_id = ?";
    private static final String FIND_COURSE_BY_ID_SQL = FIND_ALL_COURSES_SQL + " WHERE c_id = ?";
    private static final String FIND_USER_ENROLLED_BY_COURSE_SQL = "SELECT c_id, course_name, c_description, hours, cost_course " +
            "FROM training.users " +
            "INNER JOIN training.users_x_courses ON user_id = u_id " +
            "INNER JOIN training.courses ON course_id = c_id " +
            "WHERE user_id = ?";
    private static final String ADD_COURSE_SQL = "INSERT INTO courses (course_name, c_description, hours, start_course, end_course, cost_course, teacher_id) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)"; //???
    private static final String DELETE_COURSE_SQL = "DELETE FROM training.courses WHERE c_id = ?";
    private static final String UPDATE_NUMBER_OF_HOURS_SQL = "UPDATE training.courses " +
            "SET hours = ? " +
            "WHERE c_id = ?";
    private static final String UPDATE_COURSE_COST_SQL = "UPDATE training.courses " +
            "SET cost_course = ? " +
            "WHERE c_id = ?";
    private static final String UPDATE_START_END_COURSE_SQL = "UPDATE training.courses " +
            "SET start_course = ?, end_course = ? " +
            "WHERE c_id = ?";
    private static final String UPDATE_COURSE_NAME_SQL = "UPDATE training.courses SET course_name = ? WHERE c_id = ?";
    private static final String UPDATE_COURSE_DESCRIPTION_SQL = "UPDATE training.courses SET c_description = ? WHERE c_id = ?";
    private static final String TEACHER_ON_COURSE_UPDATE_SQL = "UPDATE training.courses SET teacher_id = ? WHERE c_id = ?";

    private final ConnectionPool connectionPool = ConcurrentConnectionPool.getInstance();

    private CourseDaoImpl() {
    }

    @Override
    public List<Course> findAllCoursesByTeacherId(Long teacherId) throws DaoException {
        List<Course> courses = new ArrayList<>();
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_COURSES_BY_TEACHER_ID_SQL)) {
            preparedStatement.setLong(1, teacherId);
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
    public List<Course> findUserEnrolledByCourse(Long userId) throws DaoException {
        List<Course> courses = new ArrayList<>();
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_ENROLLED_BY_COURSE_SQL)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Course course = Course.builder()
                        .setId(resultSet.getLong(ColumnName.C_ID))
                        .setName(resultSet.getString(ColumnName.COURSE_NAME))
                        .setDescription(resultSet.getString(ColumnName.COURSE_DESCRIPTION))
                        .setHours(resultSet.getInt(ColumnName.HOURS))
                        .setCost(resultSet.getBigDecimal(ColumnName.COST_COURSE))
                        .build();
                courses.add(course);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
        return courses;
    }

    @Override
    public boolean updateHours(Course course) throws DaoException {
        boolean isUpdate;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_NUMBER_OF_HOURS_SQL)) {
            preparedStatement.setInt(1, course.getHours());
            preparedStatement.setLong(2, course.getId());

            isUpdate = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean updateCourseName(Course course) throws DaoException {
        boolean isUpdate;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COURSE_NAME_SQL)) {
            preparedStatement.setString(1, course.getName());
            preparedStatement.setLong(2, course.getId());

            isUpdate = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean updateDescription(Course course) throws DaoException {
        boolean isUpdate;
        try (Connection connection = connectionPool.takeConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COURSE_DESCRIPTION_SQL)){
            preparedStatement.setString(1, course.getDescription());
            preparedStatement.setLong(2, course.getId());

            isUpdate = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean updateCost(Course course) throws DaoException {
        boolean isUpdate;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COURSE_COST_SQL)) {
            preparedStatement.setBigDecimal(1, course.getCost());
            preparedStatement.setLong(2, course.getId());

            isUpdate = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
        return isUpdate;
    }

    //???
    @Override
    public boolean updateDate(Course course) throws DaoException {
        boolean isUpdate;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_START_END_COURSE_SQL)) {
            preparedStatement.setDate(1, course.getStartCourse());
            preparedStatement.setDate(2, course.getEndCourse());
            preparedStatement.setLong(3, course.getId());

            isUpdate = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean updateTeacher(Course course) throws DaoException {
        boolean isUpdate;
        try (Connection connection = connectionPool.takeConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(TEACHER_ON_COURSE_UPDATE_SQL)){
            preparedStatement.setLong(1, course.getTeacher().getId());
            preparedStatement.setLong(2, course.getId());

            isUpdate = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
        return isUpdate;
    }

    @Override
    public List<Course> findAll() throws DaoException {
        List<Course> courses = new ArrayList<>();
        try (Connection connection = connectionPool.takeConnection();
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
    public Optional<Course> findById(Long id) throws DaoException {
        Optional<Course> courseOptional = Optional.empty();
        try (Connection connection = connectionPool.takeConnection();
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
        boolean isSaved;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_COURSE_SQL)) {
            preparedStatement.setString(1, course.getName());
            preparedStatement.setString(2, course.getDescription());
            preparedStatement.setInt(3, course.getHours());
            preparedStatement.setDate(4, course.getStartCourse());
            preparedStatement.setDate(5, course.getEndCourse());
            preparedStatement.setBigDecimal(6, course.getCost());
            preparedStatement.setLong(7, course.getTeacher().getId());

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
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COURSE_SQL)) {
            preparedStatement.setLong(1, id);
            isDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException(e);
        }
        return isDeleted;
    }

    public static CourseDaoImpl getInstance() {
        return INSTANCE;
    }

    private Course buildCourse(ResultSet resultSet) throws SQLException {
        Teacher teacher = Teacher.builder()
                .setId(resultSet.getLong(ColumnName.TEACHER_ID))
                .setName(resultSet.getString(ColumnName.TEACHER_NAME))
                .setSurname(resultSet.getString(ColumnName.TEACHER_SURNAME))
                .build();
        return Course.builder()
                .setId(resultSet.getLong(ColumnName.C_ID))
                .setName(resultSet.getString(ColumnName.COURSE_NAME))
                .setDescription(resultSet.getString(ColumnName.COURSE_DESCRIPTION))
                .setHours(resultSet.getInt(ColumnName.HOURS))
                .setStartCourse(resultSet.getDate(ColumnName.START_COURSE))
                .setEndCourse(resultSet.getDate(ColumnName.END_COURSE))
                .setCost(resultSet.getBigDecimal(ColumnName.COST_COURSE))
                .setTeacher(teacher)
                .build();
    }

}
