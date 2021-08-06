package com.epam.jwd.training.model.entity;

import java.util.Objects;

/**
 * Entity class teacher
 *
 * @author Nadzeya Zmushka
 */
public class Teacher extends BaseEntity {

    private String name;
    private String surname;

    public Teacher() {
    }

//    public Teacher(String name, String surname) {
//        this.name = name;
//        this.surname = surname;
//    }

    /**
     * Instantiates a new Teacher.
     *
     * @param id      the id
     * @param name    the name
     * @param surname the surname
     */
    public Teacher(Long id, String name, String surname) {
        super(id);
        this.name = name;
        this.surname = surname;
    }

    /**
     * Builder teacher builder.
     *
     * @return {@link TeacherBuilder}
     */
    public static TeacherBuilder builder() {
        return new TeacherBuilder();
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public static class TeacherBuilder {

        private Long id;
        private String name;
        private String surname;

        public TeacherBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public TeacherBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public TeacherBuilder setSurname(String surname) {
            this.surname = surname;
            return this;
        }


        public Teacher build() {
            return new Teacher(id, name, surname);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(name, teacher.name) &&
                Objects.equals(surname, teacher.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, surname);
    }

    @Override
    public String toString() {
        return name + " " +
                surname;
    }

}
