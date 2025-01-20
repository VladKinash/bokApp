package org.libapp.libapp.controller;

import org.libapp.libapp.entity.Author;
import org.libapp.libapp.entity.Book;
import org.libapp.libapp.service.AuthorService;
import org.libapp.libapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService; // Inject AuthorService

    @GetMapping("/{id}")
    public String getBookDetails(@PathVariable Integer id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);

        if (book.getAuthorId() != null) {
            Author author = authorService.getAuthorById(book.getAuthorId());
            model.addAttribute("author", author);
        }

        return "book-details";
    }
}