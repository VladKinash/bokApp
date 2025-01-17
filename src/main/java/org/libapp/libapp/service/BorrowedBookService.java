package org.libapp.libapp.service;

import org.libapp.libapp.entity.Book;
import org.libapp.libapp.entity.BorrowedBook;
import org.libapp.libapp.entity.User;
import org.libapp.libapp.repository.BorrowedBookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowedBookService {

    private final BorrowedBookRepo borrowedBookRepo;

    @Autowired
    public BorrowedBookService(BorrowedBookRepo borrowedBookRepo) {
        this.borrowedBookRepo = borrowedBookRepo;
    }

    public List<BorrowedBook> getAllBorrowedBooks() {
        return borrowedBookRepo.findAll();
    }


    public BorrowedBook updateBorrowedBook(Integer id, BorrowedBook updatedBorrowedBook) {
        BorrowedBook existingBorrowedBook = getBorrowedBookById(id); // Fetch existing book

        // Update fields (except for user and book, which are typically not updated)
        existingBorrowedBook.setBorrowDate(updatedBorrowedBook.getBorrowDate());
        existingBorrowedBook.setDueDate(updatedBorrowedBook.getDueDate());
        existingBorrowedBook.setReturnDate(updatedBorrowedBook.getReturnDate());

        return borrowedBookRepo.save(existingBorrowedBook);
    }

    public BorrowedBook getBorrowedBookById(Integer id) {
        return borrowedBookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Borrowed Book not found with ID: " + id));
    }
    public BorrowedBook borrowBook(User user, Book book) {
        BorrowedBook borrowedBook = new BorrowedBook();
        borrowedBook.setUser(user);
        borrowedBook.setBook(book);
        borrowedBook.setBorrowDate(LocalDate.now());
        borrowedBook.setDueDate(LocalDate.now().plusDays(14)); // Example: Due date is 14 days from borrow date

        return borrowedBookRepo.save(borrowedBook);
    }

    public BorrowedBook returnBook(Integer borrowId) {
        BorrowedBook borrowedBook = getBorrowedBookById(borrowId);
        borrowedBook.setReturnDate(LocalDate.now());

        return borrowedBookRepo.save(borrowedBook);
    }
    public void deleteBorrowedBook(Integer id) {
        BorrowedBook borrowedBook = getBorrowedBookById(id);
        borrowedBookRepo.delete(borrowedBook);
    }



}