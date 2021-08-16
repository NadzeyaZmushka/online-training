package com.epam.jwd.training.model.service.impl;

import com.epam.jwd.training.exception.DaoException;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.dao.TeacherDao;
import com.epam.jwd.training.model.dao.impl.TeacherDaoImpl;
import com.epam.jwd.training.model.entity.Teacher;
import com.epam.jwd.training.model.service.TeacherService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TeacherServiceImplTest {

    private TeacherDao teacherDao;
    private TeacherService teacherService;
    private Teacher teacher1;
    private Teacher teacher2;
    private final List<Teacher> teachers = new ArrayList<>();


    @Before
    public void setUp() {
        teacherDao = mock(TeacherDaoImpl.class);
        teacherService = new TeacherServiceImpl(teacherDao);
        teacher1 = Teacher.builder()
                .setId(1L)
                .setName("Name")
                .setSurname("Surname")
                .build();
        teacher2 = Teacher.builder()
                .setId(2L)
                .setName("Name2")
                .setSurname("Surname2")
                .build();
        teachers.add(teacher1);
        teachers.add(teacher2);
    }

    @Test
    public void test_FindAllTeachers() throws DaoException, ServiceException {
        when(teacherDao.findAll()).thenReturn(teachers);
        List<Teacher> actual = teacherService.findAll();
        assertEquals(teachers, actual);
    }

    @Test
    public void test_FindById() throws ServiceException, DaoException {
        Optional<Teacher> expectedTeacher = Optional.of(teacher2);
        when(teacherDao.findById(2L)).thenReturn(expectedTeacher);
        Optional<Teacher> actual = teacherService.findById(2L);
        assertEquals(expectedTeacher, actual);
    }

    @Test
    public void test_delete_mustDeleteTeacher() throws ServiceException, DaoException {
        when(teacherDao.delete(teacher1.getId())).thenReturn(true);
        boolean actual = teacherService.delete(teacher1.getId());
        assertTrue(actual);
    }

    @Test
    public void test_findByNameAndSurname_returnTeacherWithSuchNameSurname() throws ServiceException, DaoException {
        Optional<Teacher> expected = Optional.of(teacher1);
        when(teacherDao.findByNameAndSurname(teacher1.getName(),teacher1.getSurname()))
                .thenReturn(expected);
        Optional<Teacher> actual = teacherService.findByNameAndSurname("Name", "Surname");
        assertEquals(expected, actual);

    }

}