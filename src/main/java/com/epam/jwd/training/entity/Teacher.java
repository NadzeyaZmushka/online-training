package com.epam.jwd.training.entity;

import java.util.Objects;

public class Teacher extends BaseEntity {

    private String name;
    private String surname;
    private Course course;

    public Teacher() {
    }

    public Teacher(String name, String surname, Course course) {
        this.name = name;
        this.surname = surname;
        this.course = course;
    }

    public Teacher(Long id, String name, String surname, Course course) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
        return Objects.equals(name, teacher.name) &&
                Objects.equals(surname, teacher.surname) &&
                Objects.equals(course, teacher.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, surname, course);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", course=" + course +
                "} " + super.toString();
    }

}
