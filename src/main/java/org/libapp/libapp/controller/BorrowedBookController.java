package org.libapp.libapp.controller;

import org.libapp.libapp.entity.Book;
import org.libapp.libapp.entity.BorrowedBook;
import org.libapp.libapp.entity.User;
import org.libapp.libapp.service.BookService;
import org.libapp.libapp.service.BorrowedBookService;
import org.libapp.libapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/borrowed-books")
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

    // --- READ ---

    @GetMapping
    public String listBorrowedBooks(Model model) {
        List<BorrowedBook> borrowedBooks = borrowedBookService.getAllBorrowedBooks();
        model.addAttribute("borrowedBooks", borrowedBooks);
        return "borrowed-books"; // "borrowed-books.html"
    }

    @GetMapping("/{id}")
    public String getBorrowedBook(@PathVariable Integer id, Model model) {
        BorrowedBook borrowedBook = borrowedBookService.getBorrowedBookById(id);
        model.addAttribute("borrowedBook", borrowedBook);
        return "borrowed-book-details"; // "borrowed-book-details.html"
    }

    // --- CREATE ---

    @GetMapping("/borrow")
    public String showBorrowBookForm(Model model) {
        List<Book> availableBooks = bookService.getAllBooks(); // Or getAvailableBooks() if you have such a method
        model.addAttribute("availableBooks", availableBooks);
        return "borrow-book"; // "borrow-book.html"
    }

    @PostMapping("/borrow")
    public String borrowBook(@RequestParam("bookId") Integer bookId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        User user = userService.getUserByUsername(currentUsername);
        Book book = bookService.getBookById(bookId);

        borrowedBookService.borrowBook(user, book);

        return "redirect:/borrowed-books";
    }

    // --- UPDATE ---

    @GetMapping("/return/{id}")
    public String showReturnBookForm(@PathVariable Integer id, Model model) {
        BorrowedBook borrowedBook = borrowedBookService.getBorrowedBookById(id);
        model.addAttribute("borrowedBook", borrowedBook);
        return "return-book"; // "return-book.html"
    }

    @PostMapping("/return/{id}")
    public String returnBook(@PathVariable Integer id) {
        borrowedBookService.returnBook(id);
        return "redirect:/borrowed-books";
    }

    @GetMapping("/edit/{id}")
    public String showEditBorrowedBookForm(@PathVariable Integer id, Model model) {
        BorrowedBook borrowedBook = borrowedBookService.getBorrowedBookById(id);
        model.addAttribute("borrowedBook", borrowedBook);
        return "edit-borrowed-book"; // "edit-borrowed-book.html"
    }

    @PostMapping("/edit/{id}")
    public String updateBorrowedBook(@PathVariable Integer id, @ModelAttribute("borrowedBook") BorrowedBook updatedBorrowedBook) {
        return "redirect:/borrowed-books";
    }

    @PostMapping("/delete/{id}")
    public String deleteBorrowedBook(@PathVariable Integer id) {
        borrowedBookService.deleteBorrowedBook(id);
        return "redirect:/borrowed-books";
    }
}