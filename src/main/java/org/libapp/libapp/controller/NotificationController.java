package org.libapp.libapp.controller;

import org.libapp.libapp.entity.Notification;
import org.libapp.libapp.entity.User;
import org.libapp.libapp.service.NotificationService;
import org.libapp.libapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final UserService userService;

    @Autowired
    public NotificationController(NotificationService notificationService, UserService userService) {
        this.notificationService = notificationService;
        this.userService = userService;
    }


    @GetMapping
    public String listNotifications(Model model) {
        User currentUser = userService.getCurrentLoggedInUser();
        if (currentUser == null) {
            return "redirect:/login";
        }

        List<Notification> notifications = notificationService.getAllNotificationsForUser(currentUser);
        model.addAttribute("notifications", notifications);
        return "notifications";
    }

    @PostMapping("/send")
    @Secured({"ROLE_ADMIN", "ROLE_LIBRARIAN"})
    public String sendNotificationToUser(@RequestParam("userId") Integer userId,
                                         @RequestParam("message") String message) {
        User recipient = userService.getUserById(userId);
        notificationService.createNotification(recipient, message);
        return "redirect:/admin/manage-users";
    }


    @PostMapping("/mark-read/{notificationId}")
    public String markNotificationAsRead(@PathVariable Integer notificationId) {
        notificationService.markAsRead(notificationId);
        return "redirect:/notifications";
    }


}
