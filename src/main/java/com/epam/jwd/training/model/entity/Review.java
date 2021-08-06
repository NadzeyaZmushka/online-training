package com.epam.jwd.training.model.entity;

import java.sql.Date;
import java.util.Objects;

/**
 * Entity class review
 *
 * @author Nadzeya Zmushka
 */
public class Review extends BaseEntity {

    private String description;
    private Date date;
    private User user;

    public Review() {
    }

//    public Review(String description, Date date, User user) {
//        this.description = description;
//        this.date = date;
//        this.user = user;
//    }

    /**
     * Instantiates a new Review.
     *
     * @param id          the id
     * @param description the description
     * @param date        the date message
     * @param user        the user
     */
    public Review(Long id, String description, Date date, User user) {
        super(id);
        this.description = description;
        this.date = date;
        this.user = user;
    }

    /**
     * Builder review builder.
     *
     * @return {@link ReviewBuilder}
     */
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

    /**
     * Class Review Builder
     */
    public static class ReviewBuilder {

        private Long id;
        private String description;
        private Date date;
        private User user;

        /**
         * Sets id.
         *
         * @param id the id
         * @return the id
         */
        public ReviewBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        /**
         * Sets description.
         *
         * @param description the description
         * @return the description
         */
        public ReviewBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        /**
         * Sets date message.
         *
         * @param date the date message
         * @return the date message
         */
        public ReviewBuilder setDate(Date date) {
            this.date = date;
            return this;
        }

        /**
         * Sets user.
         *
         * @param user {@link User}
         * @return the user
         */
        public ReviewBuilder setUser(User user) {
            this.user = user;
            return this;
        }

        /**
         * Build review.
         *
         * @return {@link Review}
         */
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
