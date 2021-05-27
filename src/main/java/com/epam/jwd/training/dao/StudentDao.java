package com.epam.jwd.training.dao;

import com.epam.jwd.training.entity.Student;

import java.util.List;

public interface StudentDao extends BaseDao<Student> {

    List<Student> findAllStudentsByCourseId(long id);
}
