package org.libapp.libapp.dto;

public class AuthorBorrowCountDTO {
    private Integer authorId;
    private String firstName;
    private String lastName;
    private Long borrowCount;

    public AuthorBorrowCountDTO(Integer authorId, String firstName, String lastName, Long borrowCount) {
        this.authorId = authorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.borrowCount = borrowCount;
    }

    // Getters and setters
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

    public Long getBorrowCount() {
        return borrowCount;
    }

    public void setBorrowCount(Long borrowCount) {
        this.borrowCount = borrowCount;
    }
}