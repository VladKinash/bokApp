package org.libapp.libapp.controller;

import org.libapp.libapp.entity.Author;
import org.libapp.libapp.entity.Book;
import org.libapp.libapp.entity.BookAuthor;
import org.libapp.libapp.entity.Publisher;
import org.libapp.libapp.service.AuthorService;
import org.libapp.libapp.service.BookService;
import org.libapp.libapp.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService; // Inject AuthorService

    @Autowired
    private PublisherService publisherService; // Inject PublisherService

    // Display Book Details
    @GetMapping("/{id}")
    public String getBookDetails(@PathVariable Integer id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);

        if (book.getPublisher() != null) {
            model.addAttribute("publisher", book.getPublisher());
        }

        // Fetch authors associated with the book
        Set<Author> authors = book.getBookAuthors().stream()
                .map(BookAuthor::getAuthor)
                .collect(Collectors.toSet());
        model.addAttribute("authors", authors);

        return "book-details";
    }

    @GetMapping("/add")
    @Secured({"ROLE_ADMIN", "ROLE_LIBRARIAN"})
    public String showAddBookForm(Model model) {
        Book book = new Book();
        List<Author> authors = authorService.getAllAuthors();
        List<Publisher> publishers = publisherService.getAllPublishers();
        model.addAttribute("book", book);
        model.addAttribute("authors", authors);
        model.addAttribute("publishers", publishers);
        return "add-book";
    }

    @PostMapping("/add")
    @Secured({"ROLE_ADMIN", "ROLE_LIBRARIAN"})
    @Transactional
    public String addBook(@ModelAttribute("book") Book book,
                          @RequestParam(value = "authorIds", required = false) List<Integer> authorIds,
                          BindingResult result,
                          Model model) {
        if (result.hasErrors()) {
            List<Author> authors = authorService.getAllAuthors();
            List<Publisher> publishers = publisherService.getAllPublishers();
            model.addAttribute("authors", authors);
            model.addAttribute("publishers", publishers);
            return "add-book";
        }

        if (book.getPublisher() != null && book.getPublisher().getId() != null) {
            Publisher publisher = publisherService.getPublisherById(book.getPublisher().getId());
            book.setPublisher(publisher);
        }

        Book savedBook = bookService.addBook(book);

        if (authorIds != null && !authorIds.isEmpty()) {
            for (Integer authorId : authorIds) {
                Author author = authorService.getAuthorById(authorId);
                savedBook.addAuthor(author);
            }
        }

        return "redirect:/books/" + savedBook.getId();
    }

    @GetMapping("/edit/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_LIBRARIAN"})
    public String showEditBookForm(@PathVariable Integer id, Model model) {
        Book book = bookService.getBookById(id);
        List<Author> authors = authorService.getAllAuthors();
        List<Publisher> publishers = publisherService.getAllPublishers();
        model.addAttribute("book", book);
        model.addAttribute("authors", authors);
        model.addAttribute("publishers", publishers);

        Set<Integer> selectedAuthorIds = book.getBookAuthors().stream()
                .map(ba -> ba.getAuthor().getId())
                .collect(Collectors.toSet());
        model.addAttribute("selectedAuthorIds", selectedAuthorIds);

        return "edit-book";
    }

    @PostMapping("/edit/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_LIBRARIAN"})
    @Transactional
    public String editBook(@PathVariable Integer id,
                           @ModelAttribute("book") Book updatedBook,
                           BindingResult result,
                           @RequestParam(value = "authorIds", required = false) List<Integer> authorIds,
                           Model model) {
        if (result.hasErrors()) {
            List<Author> authors = authorService.getAllAuthors();
            List<Publisher> publishers = publisherService.getAllPublishers();
            model.addAttribute("authors", authors);
            model.addAttribute("publishers", publishers);

            Set<Integer> selectedAuthorIds = updatedBook.getBookAuthors().stream()
                    .map(ba -> ba.getAuthor().getId())
                    .collect(Collectors.toSet());
            model.addAttribute("selectedAuthorIds", selectedAuthorIds);

            return "edit-book";
        }

        if (updatedBook.getPublisher() != null && updatedBook.getPublisher().getId() != null) {
            Publisher publisher = publisherService.getPublisherById(updatedBook.getPublisher().getId());
            updatedBook.setPublisher(publisher);
        }

        Book book = bookService.updateBook(id, updatedBook);

        book.getBookAuthors().clear();

        if (authorIds != null && !authorIds.isEmpty()) {
            for (Integer authorId : authorIds) {
                Author author = authorService.getAuthorById(authorId);
                book.addAuthor(author);
            }
        }

        return "redirect:/books/" + book.getId();
    }

    @PostMapping("/delete/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_LIBRARIAN"})
    @Transactional
    public String deleteBook(@PathVariable Integer id) {
        Book book = bookService.getBookById(id);
        book.getBookAuthors().clear();
        bookService.deleteBook(id);
        return "redirect:/";
    }
}
