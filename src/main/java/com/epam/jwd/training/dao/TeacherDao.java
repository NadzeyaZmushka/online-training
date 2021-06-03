package com.epam.jwd.training.dao;

import com.epam.jwd.training.entity.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherDao extends BaseDao<Teacher> {
    //?
   Optional<Teacher> findBySurname(String surname);

}
