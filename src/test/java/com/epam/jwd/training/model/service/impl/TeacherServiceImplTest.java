package com.epam.jwd.training.model.service.impl;

import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.entity.Teacher;
import com.epam.jwd.training.model.service.TeacherService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TeacherServiceImplTest {

    private final TeacherService teacherServiceTest = TeacherServiceImpl.getInstance();

    private final Teacher expected = Teacher.builder()
            .setName("Name")
            .setSurname("Surname")
            .build();

    @Before
    public void saveTeacher() throws ServiceException {
        teacherServiceTest.save(expected);
    }

    @After
    public void deleteTeacher() throws ServiceException {
        teacherServiceTest.delete(expected.getId());
    }


    @Test
    public void test_FindById_returnTeacherWithSuchId() throws ServiceException {
        Optional<Teacher> teacherOptional = teacherServiceTest.findById(expected.getId());

        assertEquals(Optional.of(expected), teacherOptional);
    }

    @Test
    public void test_delete_mustDeleteTeacher() throws ServiceException {
        teacherServiceTest.delete(expected.getId());
        List<Teacher> allTeachers = teacherServiceTest.findAll();

        assertFalse(allTeachers.contains(expected));
    }

    @Test
    public void test_findById_returnOptionalEmpty_ifTeacherWasNotFound() throws ServiceException {
        Optional<Teacher> teacher = teacherServiceTest.findById(0L);

        assertTrue(teacher.isEmpty());
    }

    @Test
    public void test_findByNameAndSurname_returnTeacherWithSuchNameSurname() throws ServiceException {
        Optional<Teacher> actual = teacherServiceTest.findByNameAndSurname(expected.getName(), expected.getSurname());

        assertEquals(Optional.of(expected), actual);
    }

    @Test
    public void test_findByNameAndSurname_returnOptionalEmpty_ifTeacherIsNotExist() throws ServiceException {
        String name = "name1";
        Optional<Teacher> actual = teacherServiceTest.findByNameAndSurname(name, expected.getSurname());

        assertTrue(actual.isEmpty());
    }

}