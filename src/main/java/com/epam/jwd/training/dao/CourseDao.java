package com.epam.jwd.training.dao;

import com.epam.jwd.training.entity.Course;

import java.util.List;

public interface CourseDao extends BaseDao<Course> {

    List<Course> findAllCoursesByStudentId(long id);

    List<Course> findAllCoursesByTeacherId(long id);

}
