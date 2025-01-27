package org.libapp.libapp.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "book_authors", schema = "bookapp")
public class BookAuthor {
    @EmbeddedId
    private BookAuthorId id;

    @MapsId("bookId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @MapsId("authorId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    // Constructors
    public BookAuthor() {}

    public BookAuthor(Book book, Author author) {
        this.book = book;
        this.author = author;
        this.id = new BookAuthorId(book.getId(), author.getId());
    }

    // Getters and Setters

    public BookAuthorId getId() {
        return id;
    }

    public void setId(BookAuthorId id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    // Override equals and hashCode based on id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookAuthor that = (BookAuthor) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
