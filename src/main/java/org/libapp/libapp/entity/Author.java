package org.libapp.libapp.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authors", schema = "bookapp")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate IDs
    @Column(name = "author_id", nullable = false)
    private Integer id;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BookAuthor> bookAuthors = new HashSet<>();

    @Lob
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Lob
    @Column(name = "last_name")
    private String lastName;

    @Lob
    @Column(name = "biography")
    private String biography;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "date_of_death")
    private LocalDate dateOfDeath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(LocalDate dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public Set<BookAuthor> getBookAuthors() {
        return bookAuthors;
    }

    public void setBookAuthors(Set<BookAuthor> bookAuthors) {
        this.bookAuthors = bookAuthors;
    }


}