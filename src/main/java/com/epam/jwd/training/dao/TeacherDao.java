package com.epam.jwd.training.dao;

import com.epam.jwd.training.entity.Teacher;

import java.util.List;

public interface TeacherDao extends BaseDao<Teacher> {
    //?
    List<Teacher> findAllTeachersByCourseId(long id);

}
