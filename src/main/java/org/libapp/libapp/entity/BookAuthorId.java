package org.libapp.libapp.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BookAuthorId implements Serializable {

    private Integer bookId;
    private Integer authorId;

    public BookAuthorId() {}

    public BookAuthorId(Integer bookId, Integer authorId) {
        this.bookId = bookId;
        this.authorId = authorId;
    }


    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookAuthorId that = (BookAuthorId) o;

        if (!Objects.equals(bookId, that.bookId)) return false;
        return Objects.equals(authorId, that.authorId);
    }

    @Override
    public int hashCode() {
        int result = bookId != null ? bookId.hashCode() : 0;
        result = 31 * result + (authorId != null ? authorId.hashCode() : 0);
        return result;
    }
}
