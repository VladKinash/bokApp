package org.libapp.libapp.dto;

public class TopBorrowedBookDTO {

    private Long bookId;
    private String title;
    private Long borrowCount;

    public TopBorrowedBookDTO(Long bookId, String title, Long borrowCount) {
        this.bookId = bookId;
        this.title = title;
        this.borrowCount = borrowCount;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getBorrowCount() {
        return borrowCount;
    }

    public void setBorrowCount(Long borrowCount) {
        this.borrowCount = borrowCount;
    }
}
