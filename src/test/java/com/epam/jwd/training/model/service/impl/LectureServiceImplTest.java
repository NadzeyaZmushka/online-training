package com.epam.jwd.training.model.service.impl;

import com.epam.jwd.training.exception.DaoException;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.dao.LectureDao;
import com.epam.jwd.training.model.dao.impl.LectureDaoImpl;
import com.epam.jwd.training.model.entity.Course;
import com.epam.jwd.training.model.entity.Lecture;
import com.epam.jwd.training.model.service.LectureService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LectureServiceImplTest {

    private LectureDao lectureDao;
    private LectureService lectureService;
    private Lecture lecture1;
    private Lecture lecture2;
    private final List<Lecture> lectures = new ArrayList<>();

    @Before
    public void setUp() {
        lectureDao = mock(LectureDaoImpl.class);
        lectureService = new LectureServiceImpl(lectureDao);
        Course course = Course.builder()
                .setId(1L)
                .setName("Course")
                .build();
        lecture1 = Lecture.builder()
                .setId(1L)
                .setName("Name")
                .setCourse(course)
                .build();
        lecture2 = Lecture.builder()
                .setId(2L)
                .setName("Name2")
                .setCourse(course)
                .build();
        lectures.add(lecture1);
    }

    @Test
    public void test_findAllLectures() throws DaoException, ServiceException {
        when(lectureDao.findAll()).thenReturn(lectures);
        List<Lecture> actual = lectureService.findAll();
        assertEquals(lectures, actual);
    }

    @Test
    public void test_findById_returnOptionalOfLectureWithSuchId() throws DaoException, ServiceException {
        Optional<Lecture> expected = Optional.of(lecture1);
        when(lectureDao.findById(1L)).thenReturn(expected);
        Optional<Lecture> actual = lectureService.findById(1L);
        assertEquals(expected, actual);
    }

    @Test
    public void test_deleteLecture() throws DaoException, ServiceException {
        when(lectureDao.delete(anyLong())).thenReturn(true);
        boolean actual = lectureService.delete(1L);
        assertTrue(actual);
    }

    @Test
    public void test_findAllLecturesByCourseId_returnListOfLectures() throws DaoException, ServiceException {
        when(lectureDao.findAllLecturesByCourseId(anyLong())).thenReturn(lectures);
        List<Lecture> actual = lectureService.findAllLecturesByCourseId(1L);
        assertEquals(lectures, actual);
    }

    @Test
    public void test_updateLectureName() throws DaoException, ServiceException {
        when(lectureDao.update(any(Lecture.class))).thenReturn(true);
        boolean actual = lectureService.update(lecture1);
        assertTrue(actual);
    }

    @Test
    public void addLecture() throws DaoException, ServiceException {
        when(lectureDao.addLecture(any(Lecture.class))).thenReturn(true);
        boolean actual = lectureService.addLecture(lecture2);
        assertTrue(actual);
    }

    @Test
    public void findLectureByIdAndCourseId() throws DaoException, ServiceException {
        Optional<Lecture> expected = Optional.of(lecture1);
        when(lectureDao.findLectureByIdAndCourseId(anyLong(), anyLong())).thenReturn(expected);
        Optional<Lecture> actual = lectureService.findLectureByIdAndCourseId(1L, 1L);
        assertEquals(expected, actual);
    }
    
}