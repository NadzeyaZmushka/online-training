package com.epam.jwd.training.model.entity;

import java.util.Objects;

/**
 * Entity class lecture
 *
 * @author Nadzeya Zmushka
 */
public class Lecture extends BaseEntity {

    private String name;
    private Course course;

    public Lecture() {
    }

    /**
     * Instantiates a new Lecture.
     *
     * @param id     the id
     * @param name   the lecture name
     * @param course the course
     */
    public Lecture(Long id, String name, Course course) {
        super(id);
        this.name = name;
        this.course = course;
    }

    /**
     * Builder lecture builder.
     *
     * @return {@link LectureBuilder}
     */
    public static LectureBuilder builder() {
        return new LectureBuilder();
    }

    public String getName() {
        return name;
    }

    public Course getCourse() {
        return course;
    }

    /**
     * Class lecture builder
     */
    public static class LectureBuilder {
        private Long id;
        private String description;
        private Course course;

        /**
         * Sets id.
         *
         * @param id the id
         * @return the id
         */
        public LectureBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        /**
         * Sets name.
         *
         * @param name the lecture name
         * @return the name
         */
        public LectureBuilder setName(String name) {
            this.description = name;
            return this;
        }

        /**
         * Sets course.
         *
         * @param course the course
         * @return the course
         */
        public LectureBuilder setCourse(Course course) {
            this.course = course;
            return this;
        }

        /**
         * Build lecture.
         *
         * @return {@link Lecture}
         */
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
