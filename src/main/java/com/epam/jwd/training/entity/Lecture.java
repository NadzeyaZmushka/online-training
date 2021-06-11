package com.epam.jwd.training.entity;

import java.util.Objects;

public class Lecture extends BaseEntity {

    private String name;
    private Course course;

    public Lecture() {
    }

    public Lecture(String name, Course course) {
        this.name = name;
        this.course = course;
    }

    public Lecture(Long id, String name, Course course) {
        super(id);
        this.name = name;
        this.course = course;
    }

    public static TaskBuilder builder() {
        return new TaskBuilder();
    }

    public String getName() {
        return name;
    }

    public Course getCourse() {
        return course;
    }

    public static class TaskBuilder {
        private Long id;
        private String description;
        private Course course;

        public TaskBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public TaskBuilder setName(String description) {
            this.description = description;
            return this;
        }

        public TaskBuilder setCourse(Course course) {
            this.course = course;
            return this;
        }

        public Lecture build() {
            return new Lecture(id, description, course);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Lecture lecture = (Lecture) o;
        return Objects.equals(name, lecture.name) &&
                Objects.equals(course, lecture.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, course);
    }

    @Override
    public String toString() {
        return "Lecture{" + super.toString() +
                ", name='" + name + '\'' +
                ", course=" + course +
                '}';
    }

}
