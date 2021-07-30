package com.epam.jwd.training.model.entity;

import java.sql.Date;
import java.util.Objects;

public class Review extends BaseEntity {

    private String description;
    private Date date;
    private User user;

    public Review() {
    }

    public Review(String description, Date date, User user) {
        this.description = description;
        this.date = date;
        this.user = user;
    }

    public Review(Long id, String description, Date date, User user) {
        super(id);
        this.description = description;
        this.date = date;
        this.user = user;
    }

    public static ReviewBuilder builder() {
        return new ReviewBuilder();
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static class ReviewBuilder {

        private Long id;
        private String description;
        private Date date;
        private User user;

        public ReviewBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public ReviewBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public ReviewBuilder setDate(Date date) {
            this.date = date;
            return this;
        }


        public ReviewBuilder setUser(User user) {
            this.user = user;
            return this;
        }

        public Review build() {
            return new Review(id, description, date, user);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Review review = (Review) o;
        return Objects.equals(description, review.description) &&
                Objects.equals(date, review.date) &&
                Objects.equals(user, review.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), description, date, user);
    }

    @Override
    public String toString() {
        return "Review{" + super.toString() +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", user=" + user +
                '}';
    }

}
