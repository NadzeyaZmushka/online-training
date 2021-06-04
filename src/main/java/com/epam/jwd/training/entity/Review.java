package com.epam.jwd.training.entity;

import java.util.Objects;

public class Review extends BaseEntity {

    private int mark;
    private String description;
    private User user;

    public Review() {
    }

    public Review(int mark, String description, User user) {
        this.mark = mark;
        this.description = description;
        this.user = user;
    }

    public Review(Long id, int mark, String description, User user) {
        super(id);
        this.mark = mark;
        this.description = description;
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

    public User getUser() {
        return user;
    }

    public static class ReviewBuilder {

        private Long id;
        private int mark;
        private String description;
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


        public ReviewBuilder setUser(User user) {
            this.user = user;
            return this;
        }

        public Review build() {
            return new Review(id, mark, description, user);
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
                Objects.equals(user, review.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mark, description, user);
    }

    @Override
    public String toString() {
        return  " Review{" + super.toString() +
                "mark=" + mark +
                ", description='" + description + '\'' +
                ", user=" + user +
                '}';
    }
}
