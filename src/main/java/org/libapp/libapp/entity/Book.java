package org.libapp.libapp.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books", schema = "bookapp", uniqueConstraints = {
        @UniqueConstraint(name = "isbn", columnNames = {"isbn"})
})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", nullable = false)
    private Integer id;

    @Column(name = "copies_available")
    private Integer copiesAvailable;

    @Lob
    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "isbn", nullable = false)
    private String isbn;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "publisher_id", nullable = false, referencedColumnName = "publisher_id", foreignKey = @ForeignKey(name = "fk_publisher_book"))
    private Publisher publisher;

    @Lob
    @Column(name = "description")
    private String description;

    @Lob
    @Column(name = "cover_image_url")
    private String coverImageUrl;

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

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
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

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BookAuthor> bookAuthors = new HashSet<>();

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Publisher getPublisherId() {
        return publisher;
    }

    public Integer getCopiesAvailable() {
        return copiesAvailable;
    }

    public void setCopiesAvailable(Integer copiesAvailable) {
        this.copiesAvailable = copiesAvailable;
    }

    public Set<BookAuthor> getBookAuthors() {
        return bookAuthors;
    }

    public void setBookAuthors(Set<BookAuthor> bookAuthors) {
        this.bookAuthors = bookAuthors;
    }

    public void addAuthor(Author author) {
        BookAuthor bookAuthor = new BookAuthor(this, author);
        if (bookAuthors.add(bookAuthor)) {
            author.getBookAuthors().add(bookAuthor);
        }
    }

    public void removeAuthor(Author author) {
        BookAuthor bookAuthor = new BookAuthor(this, author);
        if (bookAuthors.remove(bookAuthor)) {
            author.getBookAuthors().remove(bookAuthor);
            bookAuthor.setBook(null);
            bookAuthor.setAuthor(null);
        }
    }


}
