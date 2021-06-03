package com.epam.jwd.training.entity;

import java.util.Objects;

public class Task extends BaseEntity {

    private String description;
    private Course course;

    public Task() {
    }

    public Task(Long id, String description, Course course) {
        super(id);
        this.description = description;
        this.course = course;
    }

    public static TaskBuilder builder() {
        return new TaskBuilder();
    }

    public String getDescription() {
        return description;
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

        public TaskBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public TaskBuilder setCourse(Course course) {
            this.course = course;
            return this;
        }

        public Task build() {
            return new Task(id, description, course);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Task task = (Task) o;
        return Objects.equals(description, task.description) &&
                Objects.equals(course, task.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), description, course);
    }

    @Override
    public String toString() {
        return "Task{" + super.toString() +
                ", description='" + description + '\'' +
                ", course=" + course +
                '}';
    }

}
