package com.epam.jwd.training.model.service.impl;

import com.epam.jwd.training.exception.DaoException;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.dao.CourseDao;
import com.epam.jwd.training.model.dao.impl.CourseDaoImpl;
import com.epam.jwd.training.model.entity.Course;
import com.epam.jwd.training.model.entity.Teacher;
import com.epam.jwd.training.model.service.CourseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class CourseServiceImpl implements CourseService {

    public static final CourseServiceImpl INSTANCE = new CourseServiceImpl();

    private static final Logger LOGGER = LogManager.getLogger(CourseServiceImpl.class);

    private final CourseDao courseDao = CourseDaoImpl.getInstance();

    private CourseServiceImpl() {
    }

    @Override
    public List<Course> findAll() throws ServiceException {
        List<Course> courses;
        try {
            courses = courseDao.findAll();
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return courses;
//        try {
//            return courseDao.findAll().stream()
//                    .map(course -> new CourseDto(course.getId(),
//                            course.getName(),
//                            String.format("%s, %s, %s", course.getDescription(), course.getHours(), course.getCost())
//                    ))
//                    .collect(Collectors.toList());
//        } catch (DaoException e) {
//            e.printStackTrace();
//            throw new ServiceException(e);
//        }

    }

    @Override
    public Optional<Course> findById(long id) throws ServiceException {
        Optional<Course> courseOptional;
        try {
            courseOptional = courseDao.findById(id);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return courseOptional;
    }

    @Override
    public boolean delete(long id) throws ServiceException {
        boolean isDeleted;
        try {
            isDeleted = courseDao.delete(id);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isDeleted;
    }

    @Override
    public List<Course> findAllCoursesByTeacherId(long teacherId) throws ServiceException {
        List<Course> courses;
        try {
            courses = courseDao.findAllCoursesByTeacherId(teacherId);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return courses;
    }

    @Override
    public List<Course> findUserEnrolledByCourse(long userId) throws ServiceException {
        List<Course> courses;
        try {
            courses = courseDao.findUserEnrolledByCourse(userId);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return courses;
    }

    @Override
    public boolean updateHours(Course course) throws ServiceException {
        boolean isUpdate;
        try {
            isUpdate = courseDao.updateHours(course);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean updateCost(Course course) throws ServiceException {
        boolean isUpdate;
        try {
            isUpdate = courseDao.updateCost(course);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isUpdate;
    }

    //???
    @Override
    public boolean updateDate(Course course) throws ServiceException {
        boolean isUpdate;
        try {
            isUpdate = courseDao.updateDate(course);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean save(String name, String description, String hours, String start, String end, String cost, Teacher teacher) throws ServiceException {
        boolean isSave;
        Course course = Course.builder()
                .setName(name)
                .setDescription(description)
                .setHours(Integer.parseInt(hours))
                .setStartCourse(Date.valueOf(start))
                .setEndCourse(Date.valueOf(end))
                .setCost(BigDecimal.valueOf(Long.parseLong(cost)))
                .setTeacher(teacher)
                .build();
        try {
            isSave = courseDao.save(course);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isSave;
    }

    public static CourseServiceImpl getInstance() {
        return INSTANCE;
    }

}
