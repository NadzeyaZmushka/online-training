package com.epam.jwd.training.entity;

import java.util.Objects;

public class Teacher extends Account {

    private Course course;

    public Teacher() {
    }

    public Teacher(Long id, String name, String surname, String login, String password) {
        super(id, name, surname, login, password);
    }

    public Teacher(Long id, String name, String surname, String login, String password, Course course) {
        super(id, name, surname, login, password);
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(course, teacher.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), course);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "course=" + course +
                "} " + super.toString();
    }

}
