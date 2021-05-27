package com.epam.jwd.training.entity;

import java.util.Objects;

public class Student extends Account {

    private Course course;

    public Student() {
    }

    public Student(Long id, String name, String surname, String login, String password) {
        super(id, name, surname, login, password);
    }

    public Student(Long id, String name, String surname, String login, String password, Course course) {
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
        Student student = (Student) o;
        return Objects.equals(course, student.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), course);
    }

    @Override
    public String toString() {
        return "Student{" +
                "course=" + course +
                "}" + super.toString();
    }

}