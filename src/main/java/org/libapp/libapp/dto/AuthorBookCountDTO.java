package org.libapp.libapp.dto;

public class AuthorBookCountDTO {
    private Integer authorId;
    private String firstName;
    private String lastName;
    private Long bookCount;

    public AuthorBookCountDTO(Integer authorId, String firstName, String lastName, Long bookCount) {
        this.authorId = authorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bookCount = bookCount;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
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

    public Long getBookCount() {
        return bookCount;
    }

    public void setBookCount(Long bookCount) {
        this.bookCount = bookCount;
    }

}