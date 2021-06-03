package com.epam.jwd.training.entity;

import java.util.Objects;

public class Teacher extends BaseEntity {

    private String name;
    private String surname;

    public Teacher() {
    }

    public Teacher(Long id, String name, String surname) {
        super(id);
        this.name = name;
        this.surname = surname;
    }

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
        return "Teacher{" +
                "name='" + name + '\'' +
                ", surname='" + surname +
                "} " + super.toString();
    }

}
