package org.libapp.libapp.controller;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.libapp.libapp.entity.*;
import org.libapp.libapp.repository.BookRepo;
import org.libapp.libapp.repository.RatingRepo;
import org.libapp.libapp.service.AuthorService;
import org.libapp.libapp.service.BookService;
import org.libapp.libapp.service.PublisherService;
import org.libapp.libapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final BookRepo bookRepo;
    private final UserService userService;
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final RatingRepo ratingRepository;

    @Autowired
    public BookController(BookService bookService, BookRepo bookRepo, UserService userService,
                          AuthorService authorService, PublisherService publisherService,
                          RatingRepo ratingRepository) {
        this.bookService = bookService;
        this.bookRepo = bookRepo;
        this.userService = userService;
        this.authorService = authorService;
        this.publisherService = publisherService;
        this.ratingRepository = ratingRepository;
    }

    @GetMapping("/{id}")
    public String getBookDetails(@PathVariable Integer id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);

        if (book.getPublisher() != null) {
            model.addAttribute("publisher", book.getPublisher());
        }

        Set<Author> authors = book.getBookAuthors().stream()
                .map(BookAuthor::getAuthor)
                .collect(Collectors.toSet());
        model.addAttribute("authors", authors);

        Double averageRating = ratingRepository.findAverageRatingByBookId(id);
        model.addAttribute("averageRating", averageRating != null ? averageRating : 0.0);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            User user = userService.getUserByUsername(authentication.getName());
            if (user != null) {
                boolean hasRated = ratingRepository.existsByBookIdAndUserId(id, user.getId());
                model.addAttribute("hasRated", hasRated);
            }
        }

        return "book-details";
    }

    @PostMapping("/{id}/rate")
    public String submitRating(@PathVariable Integer id,
                               @RequestParam("rating") BigDecimal rating,
                               RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUsername(authentication.getName());

        try {
            Rating newRating = new Rating();
            newRating.setBook(bookService.getBookById(id));
            newRating.setUser(user);
            newRating.setRating(rating);
            newRating.setCreatedAt(Instant.now());

            ratingRepository.save(newRating);
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "You've already rated this book!");
        }

        return "redirect:/books/" + id;
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

    @GetMapping("/search")
    public String searchBooks(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "publicationDateFrom", required = false) LocalDate publicationDateFrom,
            @RequestParam(value = "publicationDateTo", required = false) LocalDate publicationDateTo,
            Model model) {

        Specification<Book> spec = (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (keyword != null && !keyword.isEmpty()) {
                String likeKeyword = "%" + keyword.toLowerCase() + "%";

                Predicate titlePredicate = criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("title").as(String.class)),
                        likeKeyword
                );

                //using  bookauthor here because of their many-to-many relationship
                Join<Book, BookAuthor> bookAuthorJoin = root.join("bookAuthors", JoinType.LEFT);
                Join<BookAuthor, Author> authorJoin = bookAuthorJoin.join("author", JoinType.LEFT);

                Predicate authorFirstNamePredicate = criteriaBuilder.like(
                        criteriaBuilder.lower(authorJoin.get("firstName").as(String.class)),
                        likeKeyword
                );
                Predicate authorLastNamePredicate = criteriaBuilder.like(
                        criteriaBuilder.lower(authorJoin.get("lastName").as(String.class)),
                        likeKeyword
                );

                Predicate isbnPredicate = criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("isbn").as(String.class)),
                        likeKeyword
                );

                predicate = criteriaBuilder.or(titlePredicate, authorFirstNamePredicate, authorLastNamePredicate, isbnPredicate);
            }

            Predicate datePredicate = criteriaBuilder.conjunction();

            if (publicationDateFrom != null) {
                Predicate fromPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("publicationDate"), publicationDateFrom);
                Predicate isNullPredicate = criteriaBuilder.isNull(root.get("publicationDate"));
                datePredicate = criteriaBuilder.and(datePredicate, criteriaBuilder.or(fromPredicate, isNullPredicate));
            }

            if (publicationDateTo != null) {
                Predicate toPredicate = criteriaBuilder.lessThanOrEqualTo(root.get("publicationDate"), publicationDateTo);
                Predicate isNullPredicate = criteriaBuilder.isNull(root.get("publicationDate"));
                datePredicate = criteriaBuilder.and(datePredicate, criteriaBuilder.or(toPredicate, isNullPredicate));
            }

            if (publicationDateFrom != null || publicationDateTo != null) {
                predicate = criteriaBuilder.and(predicate, datePredicate);
            }

            return predicate;
        };

        List<Book> searchResults = bookRepo.findAll(spec);
        model.addAttribute("books", searchResults);
        return "home";
    }
}
