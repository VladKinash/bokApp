package org.libapp.libapp.controller;

import org.libapp.libapp.entity.Author;
import org.libapp.libapp.entity.Book;
import org.libapp.libapp.entity.Publisher;
import org.libapp.libapp.service.AuthorService;
import org.libapp.libapp.service.BookService;
import org.libapp.libapp.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            Publisher publisher = publisherService.getPublisherById(book.getPublisher().getId());
            model.addAttribute("publisher", publisher);
        }

        if (book.getAuthorId() != null) {
            Author author = authorService.getAuthorById(book.getAuthorId());
            model.addAttribute("author", author);
        }

        return "book-details";
    }

    // Display Add Book Form
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

    // Handle Add Book Submission
    @PostMapping("/add")
    @Secured({"ROLE_ADMIN", "ROLE_LIBRARIAN"})
    public String addBook(@ModelAttribute("book") Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Author> authors = authorService.getAllAuthors();
            List<Publisher> publishers = publisherService.getAllPublishers();
            model.addAttribute("authors", authors);
            model.addAttribute("publishers", publishers);
            return "add-book";
        }

        // Set Publisher object based on selected publisher ID
        if (book.getPublisher() != null && book.getPublisher().getId() != null) {
            Publisher publisher = publisherService.getPublisherById(book.getPublisher().getId());
            book.setPublisher(publisher);
        }

        bookService.addBook(book);
        return "redirect:/";
    }

    // Display Edit Book Form
    @GetMapping("/edit/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_LIBRARIAN"})
    public String showEditBookForm(@PathVariable Integer id, Model model) {
        Book book = bookService.getBookById(id);
        List<Author> authors = authorService.getAllAuthors();
        List<Publisher> publishers = publisherService.getAllPublishers();
        model.addAttribute("book", book);
        model.addAttribute("authors", authors);
        model.addAttribute("publishers", publishers);
        return "edit-book";
    }

    // Handle Edit Book Submission
    @PostMapping("/edit/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_LIBRARIAN"})
    public String editBook(@PathVariable Integer id, @ModelAttribute("book") Book updatedBook, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Author> authors = authorService.getAllAuthors();
            List<Publisher> publishers = publisherService.getAllPublishers();
            model.addAttribute("authors", authors);
            model.addAttribute("publishers", publishers);
            return "edit-book";
        }

        // Set Publisher object based on selected publisher ID
        if (updatedBook.getPublisher() != null && updatedBook.getPublisher().getId() != null) {
            Publisher publisher = publisherService.getPublisherById(updatedBook.getPublisher().getId());
            updatedBook.setPublisher(publisher);
        }

        bookService.updateBook(id, updatedBook);
        return "redirect:/";
    }

    // Handle Delete Book
    @PostMapping("/delete/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_LIBRARIAN"})
    public String deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
        return "redirect:/";
    }

    @GetMapping
    public String getAllBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "home"; // Assuming the home page is named 'home.html'
    }
}
