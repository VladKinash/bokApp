package org.libapp.libapp.service;

import org.libapp.libapp.entity.Book;
import org.libapp.libapp.repository.BookRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepo bookRepo;

    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    public Book addBook(Book book) {
        return bookRepo.save(book);
    }

    public Book getBookById(Long id) {
        return bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    public void deleteBook(Long id) {
        bookRepo.deleteById(id);
    }
}
