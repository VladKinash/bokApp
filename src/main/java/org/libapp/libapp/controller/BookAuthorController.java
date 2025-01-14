package org.libapp.libapp.controller;

import org.libapp.libapp.entity.BookAuthor;
import org.libapp.libapp.entity.BookAuthorId;
import org.libapp.libapp.service.BookAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book-authors")
public class BookAuthorController {

    private final BookAuthorService bookAuthorService;

    @Autowired
    public BookAuthorController(BookAuthorService bookAuthorService) {
        this.bookAuthorService = bookAuthorService;
    }

    @GetMapping
    public List<BookAuthor> getAllBookAuthors() {
        return bookAuthorService.getAllBookAuthors();
    }


    @PostMapping
    public BookAuthor createBookAuthor(@RequestBody BookAuthor bookAuthor) {
        return bookAuthorService.addBookAuthor(bookAuthor);
    }


    @GetMapping("/{bookId}/{authorId}")
    public BookAuthor getBookAuthorById(@PathVariable Integer bookId, @PathVariable Integer authorId) {
        return bookAuthorService.getBookAuthorById(new BookAuthorId(bookId, authorId));
    }


    @DeleteMapping("/{bookId}/{authorId}")
    public void deleteBookAuthor(@PathVariable Integer bookId, @PathVariable Integer authorId) {
        bookAuthorService.deleteBookAuthor(new BookAuthorId(bookId, authorId));
    }
}
