package com.epam.jwd.training.entity;

import java.util.Objects;

public class User extends BaseEntity {

    private String name;
    private String surname;
    private String email;
    private RoleType role;
    private Course course;
    private boolean enabled;

    public User() {
    }

    public User(String name, String surname,
                String email,
                RoleType role, boolean enabled) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
        this.enabled = enabled;

    }

    public User(Long id, String name, String surname,
                String email,
                RoleType role, Course course, boolean enabled) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
        this.course = course;
        this.enabled = enabled;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public RoleType getRole() {
        return role;
    }

    public Course getCourse() {
        return course;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public static class UserBuilder {

        private Long id;
        private String name;
        private String surname;
        private String email;
        private RoleType role;
        private Course course;
        private boolean enabled;

        public UserBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setRole(RoleType role) {
            this.role = role;
            return this;
        }

        public UserBuilder setCourse(Course course) {
            this.course = course;
            return this;
        }

        public UserBuilder setEnabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public User build() {
            return new User(id, name, surname, email, role, course, enabled);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return enabled == user.enabled &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(email, user.email) &&
                role == user.role &&
                Objects.equals(course, user.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, surname, email, role, course, enabled);
    }

    @Override
    public String toString() {
        return "User{" + super.toString() + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", course='" + course + '\'' +
                ", enabled='" + enabled + '\'' +
                '}';
    }

}
