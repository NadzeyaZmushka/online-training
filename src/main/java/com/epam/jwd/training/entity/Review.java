package com.epam.jwd.training.entity;

import java.util.Objects;

public class Review extends BaseEntity {

    private int mark;
    private String description;
    private Task task;
    private Teacher teacher;
    private User user;

    public Review() {
    }

    public Review(Long id, int mark, String description, Task task, Teacher teacher, User user) {
        super(id);
        this.mark = mark;
        this.description = description;
        this.task = task;
        this.teacher = teacher;
        this.user = user;
    }

    public static ReviewBuilder builder() {
        return new ReviewBuilder();
    }

    public int getMark() {
        return mark;
    }

    public String getDescription() {
        return description;
    }

    public Task getTask() {
        return task;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public User getUser() {
        return user;
    }

    public static class ReviewBuilder {

        private Long id;
        private int mark;
        private String description;
        private Task task;
        private Teacher teacher;
        private User user;

        public ReviewBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public ReviewBuilder setMark(int mark) {
            this.mark = mark;
            return this;
        }

        public ReviewBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public ReviewBuilder setTask(Task task) {
            this.task = task;
            return this;
        }

        public ReviewBuilder setTeacher(Teacher teacher) {
            this.teacher = teacher;
            return this;
        }

        public ReviewBuilder setUser(User user) {
            this.user = user;
            return this;
        }

        public Review build() {
            return new Review(id, mark, description, task, teacher, user);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Review review = (Review) o;
        return mark == review.mark &&
                Objects.equals(description, review.description) &&
                Objects.equals(teacher, review.teacher) &&
                Objects.equals(user, review.user) &&
                Objects.equals(task, review.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mark, description, task, teacher, user);
    }

    @Override
    public String toString() {
        return "Review{" +
                "mark=" + mark +
                ", description='" + description + '\'' +
                ", task=" + task +
                ", teacher=" + teacher +
                ", student=" + user +
                "} " + super.toString();
    }

}
