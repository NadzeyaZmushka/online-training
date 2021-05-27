package com.epam.jwd.training.entity;

import java.util.Objects;

public class Review extends BaseEntity {

    private int mark;
    private String description;
    private Teacher teacher;
    private Student student;
    private Task task;

    public Review() {
    }

    public Review(Long id, int mark, String description, Teacher teacher, Student student, Task task) {
        super(id);
        this.mark = mark;
        this.description = description;
        this.teacher = teacher;
        this.student = student;
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

    public Student getStudent() {
        return student;
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

    public void setStudent(Student student) {
        this.student = student;
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
                Objects.equals(student, review.student) &&
                Objects.equals(task, review.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mark, description, teacher, student, task);
    }

    @Override
    public String toString() {
        return "Review{" +
                "mark=" + mark +
                ", description='" + description + '\'' +
                ", teacher=" + teacher +
                ", student=" + student +
                ", task=" + task +
                "} " + super.toString();
    }

}
