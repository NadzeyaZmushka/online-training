package com.epam.jwd.training.model.service.impl;

import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.entity.Course;
import com.epam.jwd.training.model.entity.Teacher;
import com.epam.jwd.training.model.service.CourseService;
import com.epam.jwd.training.model.service.TeacherService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class CourseServiceTest {

    private final CourseService courseServiceTest = CourseServiceImpl.getInstance();
    private final TeacherService teacherServiceTest = TeacherServiceImpl.getInstance();

    private final Course expected = Course.builder()
            .setName("Course")
            .setDescription("Description")
            .setStartCourse(Date.valueOf(LocalDate.now()))
            .setEndCourse(Date.valueOf(LocalDate.now().plusMonths(5)))
            .setHours(88)
            .setCost(BigDecimal.valueOf(888.88))
            .setTeacher(null)
            .build();

    @Before
    public void saveCourse() throws Exception {
        Teacher teacher = teacherServiceTest.findById(1L).get();
        expected.setTeacher(teacher);
        courseServiceTest.save(expected);
    }

    @After
    public void deleteCourse() throws Exception {
        courseServiceTest.delete(expected.getId());
    }

    @Test
    public void test_findById_returnCourseWithSuchId() throws ServiceException {
        Optional<Course> actual = courseServiceTest.findById(expected.getId());

        assertEquals(Optional.of(expected), actual);
    }
}