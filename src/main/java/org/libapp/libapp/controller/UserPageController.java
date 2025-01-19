package org.libapp.libapp.controller;

import org.libapp.libapp.entity.BorrowedBook;
import org.libapp.libapp.entity.User;
import org.libapp.libapp.service.BorrowedBookService;
import org.libapp.libapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserPageController {

    private final UserService userService;
    private final BorrowedBookService borrowedBookService;

    @Autowired
    public UserPageController(UserService userService, BorrowedBookService borrowedBookService) {
        this.userService = userService;
        this.borrowedBookService = borrowedBookService;
    }

    @GetMapping("/profile")
    public String showUserProfile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = auth.getName();

        User user = userService.getUserByUsername(currentUsername);

        List<BorrowedBook> borrowedBooks = borrowedBookService.getBorrowedBooksByUser(user);

        model.addAttribute("user", user);
        model.addAttribute("borrowedBooks", borrowedBooks);

        return "user-profile";
    }
}
