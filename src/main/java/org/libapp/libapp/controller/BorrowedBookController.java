package org.libapp.libapp.controller;

import org.libapp.libapp.entity.Book;
import org.libapp.libapp.entity.BorrowedBook;
import org.libapp.libapp.entity.User;
import org.libapp.libapp.service.BookService;
import org.libapp.libapp.service.BorrowedBookService;
import org.libapp.libapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrowed-books") // Added "/api" to indicate it's an API endpoint
public class BorrowedBookController {

    private final BorrowedBookService borrowedBookService;
    private final UserService userService;
    private final BookService bookService;

    @Autowired
    public BorrowedBookController(BorrowedBookService borrowedBookService, UserService userService, BookService bookService) {
        this.borrowedBookService = borrowedBookService;
        this.userService = userService;
        this.bookService = bookService;
    }


    @GetMapping
    public ResponseEntity<List<BorrowedBook>> getAllBorrowedBooks() {
        List<BorrowedBook> borrowedBooks = borrowedBookService.getAllBorrowedBooks();
        return new ResponseEntity<>(borrowedBooks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowedBook> getBorrowedBookById(@PathVariable Integer id) {
        BorrowedBook borrowedBook = borrowedBookService.getBorrowedBookById(id);
        return new ResponseEntity<>(borrowedBook, HttpStatus.OK);
    }


    @PostMapping("/borrow")
    public ResponseEntity<BorrowedBook> borrowBook(@RequestParam("bookId") Integer bookId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        User user = userService.getUserByUsername(currentUsername);
        Book book = bookService.getBookById(bookId);

        BorrowedBook borrowedBook = borrowedBookService.borrowBook(user, book);
        return new ResponseEntity<>(borrowedBook, HttpStatus.CREATED);
    }


    @PutMapping("/return/{id}")
    public ResponseEntity<BorrowedBook> returnBook(@PathVariable Integer id) {
        BorrowedBook returnedBook = borrowedBookService.returnBook(id);
        return new ResponseEntity<>(returnedBook, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BorrowedBook> updateBorrowedBook(@PathVariable Integer id, @RequestBody BorrowedBook updatedBorrowedBook) {
        BorrowedBook savedBorrowedBook = borrowedBookService.updateBorrowedBook(id, updatedBorrowedBook);
        return new ResponseEntity<>(savedBorrowedBook, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrowedBook(@PathVariable Integer id) {
        borrowedBookService.deleteBorrowedBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); //204 code is ok when deleting
    }
}