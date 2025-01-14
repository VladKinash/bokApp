package org.libapp.libapp.service;

import org.libapp.libapp.entity.BookAuthor;
import org.libapp.libapp.entity.BookAuthorId;
import org.libapp.libapp.exception.ResourceNotFoundException;
import org.libapp.libapp.repository.BookAuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookAuthorService {

    private final BookAuthorRepo bookAuthorRepo;

    @Autowired
    public BookAuthorService(BookAuthorRepo bookAuthorRepo) {
        this.bookAuthorRepo = bookAuthorRepo;
    }

    public List<BookAuthor> getAllBookAuthors() {
        return bookAuthorRepo.findAll();
    }

    public BookAuthor addBookAuthor(BookAuthor bookAuthor) {
        return bookAuthorRepo.save(bookAuthor);
    }

    public BookAuthor getBookAuthorById(BookAuthorId id) {
        return bookAuthorRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BookAuthor relationship not found for id: " + id));
    }

    public void deleteBookAuthor(BookAuthorId id) {
        BookAuthor bookAuthor = getBookAuthorById(id);
        bookAuthorRepo.delete(bookAuthor);
    }
}
