package com.epam.jwd.training.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Course extends BaseEntity {

    private String name;
    private String description;
    private LocalDate startCourse;
    private LocalDate endCourse;
    private BigDecimal cost;
    private Teacher teacher;

    public Course() {
    }

    public Course(Long id, String name, String description, BigDecimal cost, Teacher teacher) {
        super(id);
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.teacher = teacher;
    }

    public Course(Long id, String name, String description,
                  LocalDate startCourse, LocalDate endCourse,
                  BigDecimal cost, Teacher teacher) {
        super(id);
        this.name = name;
        this.description = description;
        this.startCourse = startCourse;
        this.endCourse = endCourse;
        this.cost = cost;
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getStartCourse() {
        return startCourse;
    }

    public LocalDate getEndCourse() {
        return endCourse;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartCourse(LocalDate startCourse) {
        this.startCourse = startCourse;
    }

    public void setEndCourse(LocalDate endCourse) {
        this.endCourse = endCourse;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(name, course.name) &&
                Objects.equals(description, course.description) &&
                Objects.equals(startCourse, course.startCourse) &&
                Objects.equals(endCourse, course.endCourse) &&
                Objects.equals(cost, course.cost) &&
                Objects.equals(teacher, course.teacher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, startCourse, endCourse, cost, teacher);
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startCourse=" + startCourse +
                ", endCourse=" + endCourse +
                ", cost=" + cost +
                ", teacher=" + teacher +
                "}" + super.toString();
    }

}
