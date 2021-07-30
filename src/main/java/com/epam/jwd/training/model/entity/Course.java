package com.epam.jwd.training.model.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

public class Course extends BaseEntity {

    private String name;
    private String description;
    private int hours;
    private Date startCourse;
    private Date endCourse;
    private BigDecimal cost;
    private Teacher teacher;

    public Course() {
    }

    public Course(Long id, String name, String description, int hours,
                  Date startCourse, Date endCourse,
                  BigDecimal cost, Teacher teacher) {
        super(id);
        this.name = name;
        this.description = description;
        this.hours = hours;
        this.startCourse = startCourse;
        this.endCourse = endCourse;
        this.cost = cost;
        this.teacher = teacher;
    }

    public static CourseBuilder builder() {
        return new CourseBuilder();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getHours() {
        return hours;
    }

    public Date getStartCourse() {
        return startCourse;
    }

    public Date getEndCourse() {
        return endCourse;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public static class CourseBuilder {

        private Long id;
        private String name;
        private String description;
        private int hours;
        private Date startCourse;
        private Date endCourse;
        private BigDecimal cost;
        private Teacher teacher;

        public CourseBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public CourseBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public CourseBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public CourseBuilder setHours(int hours) {
            this.hours = hours;
            return this;
        }

        public CourseBuilder setStartCourse(Date startCourse) {
            this.startCourse = startCourse;
            return this;
        }

        public CourseBuilder setEndCourse(Date endCourse) {
            this.endCourse = endCourse;
            return this;
        }

        public CourseBuilder setCost(BigDecimal cost) {
            this.cost = cost;
            return this;
        }

        public CourseBuilder setTeacher(Teacher teacher) {
            this.teacher = teacher;
            return this;
        }

        public Course build() {
            return new Course(id, name, description, hours, startCourse, endCourse, cost, teacher);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Course course = (Course) o;
        return hours == course.hours &&
                Objects.equals(name, course.name) &&
                Objects.equals(description, course.description) &&
                Objects.equals(startCourse, course.startCourse) &&
                Objects.equals(endCourse, course.endCourse) &&
                Objects.equals(cost, course.cost) &&
                Objects.equals(teacher, course.teacher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, hours, startCourse, endCourse, cost, teacher);
    }

    @Override
    public String toString() {
        return "Course{" + super.toString() +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", hours=" + hours +
                ", startCourse=" + startCourse +
                ", endCourse=" + endCourse +
                ", cost=" + cost +
                ", teacher=" + teacher +
                '}';
    }

}
