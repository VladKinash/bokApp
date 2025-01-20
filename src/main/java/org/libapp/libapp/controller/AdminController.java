package org.libapp.libapp.controller;

import org.libapp.libapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/manage-users")
    public String manageUsers(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "manage-users";
    }

    @GetMapping("/return-requests")
    public String manageReturnRequests(Model model) {
        return "manage-return-requests";
    }
}
