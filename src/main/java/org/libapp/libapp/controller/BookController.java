package org.libapp.libapp.controller;

import org.libapp.libapp.entity.Book;
import org.libapp.libapp.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller // Use @Controller for Thymeleaf rendering
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/{id}")
    public String getBookDetails(@PathVariable Integer id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "book-details"; // Renders book-details.html
    }


}