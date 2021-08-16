package com.epam.jwd.training.model.service.impl;

import com.epam.jwd.training.exception.DaoException;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.dao.CourseDao;
import com.epam.jwd.training.model.dao.impl.CourseDaoImpl;
import com.epam.jwd.training.model.entity.Course;
import com.epam.jwd.training.model.service.CourseService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CourseServiceImplTest {

    private CourseDao courseDao;
    private CourseService courseService;
    private Course course1;
    private Course course2;
    private final List<Course> courses = new ArrayList<>();

    @Before
    public void setUp() {
        courseDao = mock(CourseDaoImpl.class);
        courseService = new CourseServiceImpl(courseDao);
        course1 = Course.builder()
                .setId(1L)
                .setName("Course")
                .setDescription("Description")
                .setStartCourse(Date.valueOf(LocalDate.now()))
                .setEndCourse(Date.valueOf(LocalDate.now().plusMonths(5)))
                .setHours(88)
                .setCost(BigDecimal.valueOf(888.88))
                .build();
        course2 = Course.builder()
                .setId(2L)
                .setName("Course2")
                .setDescription("Description2")
                .setStartCourse(Date.valueOf(LocalDate.now()))
                .setEndCourse(Date.valueOf(LocalDate.now().plusMonths(7)))
                .setHours(99)
                .setCost(BigDecimal.valueOf(888.99))
                .build();
        courses.add(course1);
        courses.add(course2);
    }

    @Test
    public void test_findAllCourses() throws DaoException, ServiceException {
        when(courseDao.findAll()).thenReturn(courses);
        List<Course> actual = courseService.findAll();
        assertEquals(courses, actual);
    }

    @Test
    public void test_findById_returnCourseWithSuchId() throws ServiceException, DaoException {
        when(courseDao.findById(course1.getId())).thenReturn(Optional.of(course1));
        Optional<Course> actual = courseService.findById(1L);
        assertEquals(Optional.of(course1), actual);
    }


    @Test
    public void test_findUserEnrolledByCourse_returnListOfCourses() throws DaoException, ServiceException {
        when(courseDao.findUserEnrolledByCourse(anyLong())).thenReturn(courses);
        List<Course> actual = courseService.findUserEnrolledByCourse(3L);
        assertEquals(courses, actual);
    }

    @Test
    public void test_updateCourseName() throws DaoException, ServiceException {
        when(courseDao.updateCourseName(course1))
                .thenReturn(true);
        boolean actual = courseService.updateCourseName(course1);
        assertTrue(actual);
    }

    @Test
    public void test_deleteCourse() throws DaoException, ServiceException  {
        when(courseDao.delete(Mockito.anyLong()))
                .thenReturn(true);
        boolean actual = courseService.delete(1L);
        assertTrue(actual);
    }

    @Test
    public void test_updateHours() throws ServiceException, DaoException {
        when(courseDao.updateHours(course2)).thenReturn(true);
        boolean actual = courseService.updateHours(course2);
        assertTrue(actual);
    }
}