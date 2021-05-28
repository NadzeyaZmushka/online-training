package com.epam.jwd.training.entity;

import java.util.Objects;

public class Review extends BaseEntity {

    private int mark;
    private String description;
    private Teacher teacher;
    private User user;
    private Task task;

    public Review() {
    }

    public Review(Long id, int mark, String description, Teacher teacher, User user, Task task) {
        super(id);
        this.mark = mark;
        this.description = description;
        this.teacher = teacher;
        this.user = user;
        this.task = task;
    }

    public int getMark() {
        return mark;
    }

    public String getDescription() {
        return description;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public User getUser() {
        return user;
    }

    public Task getTask() {
        return task;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTask(Task task) {
        this.task = task;
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
        return Objects.hash(super.hashCode(), mark, description, teacher, user, task);
    }

    @Override
    public String toString() {
        return "Review{" +
                "mark=" + mark +
                ", description='" + description + '\'' +
                ", teacher=" + teacher +
                ", student=" + user +
                ", task=" + task +
                "} " + super.toString();
    }

}
