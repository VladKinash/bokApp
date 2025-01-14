package org.libapp.libapp.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "reviews", schema = "bookapp", uniqueConstraints = {
        @UniqueConstraint(name = "reviews_pk", columnNames = {"book_id", "user_id"})
})
public class Review {
    @Id
    @Column(name = "review_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "review_text")
    private Integer reviewText;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "review_date")
    private Instant reviewDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getReviewText() {
        return reviewText;
    }

    public void setReviewText(Integer reviewText) {
        this.reviewText = reviewText;
    }

    public Instant getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Instant reviewDate) {
        this.reviewDate = reviewDate;
    }

}