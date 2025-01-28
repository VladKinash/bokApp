package org.libapp.libapp.controller;

import org.libapp.libapp.entity.BorrowedBook;
import org.libapp.libapp.service.BookService;
import org.libapp.libapp.service.BorrowedBookService;
import org.libapp.libapp.service.RoleService;
import org.libapp.libapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final BorrowedBookService borrowedBookService;
    private final BookService bookService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService,
                           BorrowedBookService borrowedBookService,
                           BookService bookService,
                           RoleService roleService) {
        this.userService = userService;
        this.borrowedBookService = borrowedBookService;
        this.bookService = bookService;
        this.roleService = roleService;
    }

    @GetMapping("/manage-users")
    public String manageUsers(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "manage-users";
    }

    @GetMapping("/return-requests")
    public String listReturnRequests(Model model) {
        List<BorrowedBook> returnRequests = borrowedBookService.getBooksWithReturnRequested();
        model.addAttribute("returnRequests", returnRequests);
        return "return-requests";
    }

    @GetMapping("/approve-return/{borrowId}")
    public String approveReturn(@PathVariable Integer borrowId, RedirectAttributes redirectAttributes) {
        try {
            BorrowedBook borrowedBook = borrowedBookService.getBorrowedBookById(borrowId);
            borrowedBook.setReturnDate(LocalDate.now());
            borrowedBook.setReturnRequestedAt(null);

            borrowedBookService.updateBorrowedBook(borrowId, borrowedBook);
            bookService.incrementCopiesAvailable(borrowedBook.getBook().getId(), 1); // Increment book copies

            redirectAttributes.addFlashAttribute("successMessage", "Return approved successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error approving return: " + e.getMessage());
        }
        return "redirect:/admin/return-requests";
    }
}
