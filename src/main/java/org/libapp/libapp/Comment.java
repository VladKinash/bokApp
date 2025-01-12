package org.libapp.libapp;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "comments", schema = "bookapp")
public class Comment {
    @Id
    @Column(name = "comment_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private org.libapp.libapp.User user;

    @Lob
    @Column(name = "comment_text", nullable = false)
    private String commentText;

    @Column(name = "comment_date", nullable = false)
    private LocalDate commentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private org.libapp.libapp.Review review;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public org.libapp.libapp.User getUser() {
        return user;
    }

    public void setUser(org.libapp.libapp.User user) {
        this.user = user;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public LocalDate getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDate commentDate) {
        this.commentDate = commentDate;
    }

    public org.libapp.libapp.Review getReview() {
        return review;
    }

    public void setReview(org.libapp.libapp.Review review) {
        this.review = review;
    }

}