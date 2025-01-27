package org.libapp.libapp.controller;

import org.libapp.libapp.entity.Author;
import org.libapp.libapp.entity.Book;
import org.libapp.libapp.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String getAllAuthors(Model model) {
        List<Author> authors = authorService.getAllAuthors();
        model.addAttribute("authors", authors);
        return "authors";
    }

    @GetMapping("/add")
    @Secured({"ROLE_ADMIN", "ROLE_LIBRARIAN"})
    public String showAddAuthorForm(Model model) {
        Author author = new Author();
        model.addAttribute("author", author);
        return "add-author";
    }

    @PostMapping("/add")
    @Secured({"ROLE_ADMIN", "ROLE_LIBRARIAN"})
    public String addAuthor(@ModelAttribute("author") Author author, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-author";
        }
        authorService.addAuthor(author);
        return "redirect:/authors";
    }

    @GetMapping("/edit/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_LIBRARIAN"})
    public String showEditAuthorForm(@PathVariable Integer id, Model model) {
        Author author = authorService.getAuthorById(id);
        model.addAttribute("author", author);
        return "edit-author";
    }

    @PostMapping("/edit/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_LIBRARIAN"})
    public String editAuthor(@PathVariable Integer id, @ModelAttribute("author") Author updatedAuthor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit-author";
        }
        authorService.updateAuthor(id, updatedAuthor);
        return "redirect:/authors";
    }

        @PostMapping("/delete/{id}")
        @Secured({"ROLE_ADMIN", "ROLE_LIBRARIAN"})
        public String deleteAuthor(@PathVariable Integer id) {
            authorService.deleteAuthor(id);
            return "redirect:/authors";
        }

        @GetMapping("/{id}/books")
        public String getBooksByAuthor(@PathVariable Integer id, Model model) {
            Author author = authorService.getAuthorById(id);
            List<Book> books = authorService.getBooksByAuthorId(id);

            model.addAttribute("books", books);
            model.addAttribute("authorName", author.getFirstName() + " " + author.getLastName());
            return "author-books";
        }
}
