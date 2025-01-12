package org.libapp.libapp;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "books", schema = "bookapp", uniqueConstraints = {
        @UniqueConstraint(name = "isbn", columnNames = {"isbn"})
})
public class Book {
    @Id
    @Column(name = "book_id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "isbn")
    private String isbn;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @Column(name = "publisher_id")
    private Integer publisherId;

    @Lob
    @Column(name = "description")
    private String description;

    @Lob
    @Column(name = "cover_image_url")
    private String coverImageUrl;

    @Column(name = "author_id")
    private Integer authorId;

    @Lob
    @Column(name = "genre")
    private String genre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

}