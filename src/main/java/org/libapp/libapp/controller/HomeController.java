package org.libapp.libapp.controller;

import org.libapp.libapp.entity.Book;
import org.libapp.libapp.entity.Notification;
import org.libapp.libapp.entity.User;
import org.libapp.libapp.service.BookService;
import org.libapp.libapp.service.NotificationService;
import org.libapp.libapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private BookService bookService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);

        User currentUser = userService.getCurrentLoggedInUser();
        if (currentUser != null) {
            List<Notification> unreadNotifications = notificationService.getUnreadNotificationsForUser(currentUser);
            model.addAttribute("unreadNotifications", unreadNotifications);
            model.addAttribute("unreadCount", unreadNotifications.size());
        }

        return "home";
    }



}