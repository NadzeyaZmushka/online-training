package com.epam.jwd.training.model.entity;

import java.util.Objects;

/**
 * Entity class user
 *
 * @author Nadzeya Zmushka
 */
public class User extends BaseEntity {

    private String name;
    private String surname;
    private String email;
    private RoleType role;
    private Course course;
    private boolean enabled;

    public User() {
    }

    /**
     * Instantiates a new User.
     *
     * @param name    the name
     * @param surname the surname
     * @param email   the email
     * @param role    the role {@link RoleType}
     * @param enabled the enabled
     */
    public User(String name, String surname,
                String email,
                RoleType role, boolean enabled) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
        this.enabled = enabled;

    }

    /**
     * Instantiates a new User.
     *
     * @param id      the id
     * @param name    the name
     * @param surname the surname
     * @param email   the email
     * @param role    the role {@link RoleType}
     * @param enabled the enabled
     */
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

    /**
     * Builder user builder.
     *
     * @return the user builder
     */
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

    public void setRole(RoleType role) {
        this.role = role;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Class user builder
     */
    public static class UserBuilder {

        private Long id;
        private String name;
        private String surname;
        private String email;
        private RoleType role;
        private Course course;
        private boolean enabled;

        /**
         * Sets id.
         *
         * @param id the id
         * @return the id
         */
        public UserBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        /**
         * Sets name.
         *
         * @param name the name
         * @return the name
         */
        public UserBuilder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets surname.
         *
         * @param surname the surname
         * @return the surname
         */
        public UserBuilder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        /**
         * Sets email.
         *
         * @param email the email
         * @return the email
         */
        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        /**
         * Sets role.
         *
         * @param role the role {@link RoleType}
         * @return the role
         */
        public UserBuilder setRole(RoleType role) {
            this.role = role;
            return this;
        }

        /**
         * Sets course.
         *
         * @param course the course
         * @return the course
         */
        public UserBuilder setCourse(Course course) {
            this.course = course;
            return this;
        }

        /**
         * Sets enabled.
         *
         * @param enabled the enabled
         * @return the enabled
         */
        public UserBuilder setEnabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        /**
         * Build user
         *
         * @return {@link User}
         */
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
