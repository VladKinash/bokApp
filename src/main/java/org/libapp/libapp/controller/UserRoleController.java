package org.libapp.libapp.controller;

import org.libapp.libapp.entity.UserRole;
import org.libapp.libapp.entity.UserRoleId;
import org.libapp.libapp.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-roles")
public class UserRoleController {

    private final UserRoleService userRoleService;

    @Autowired
    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping
    public List<UserRole> getAllUserRoles() {
        return userRoleService.getAllUserRoles();
    }


    @PostMapping
    public UserRole createUserRole(@RequestBody UserRole userRole) {
        return userRoleService.addUserRole(userRole);
    }


    @GetMapping("/{userId}/{roleId}")
    public UserRole getUserRoleById(@PathVariable Integer userId, @PathVariable Integer roleId) {
        return userRoleService.getUserRoleById(new UserRoleId(userId, roleId));
    }


    @GetMapping("/user/{userId}")
    public List<UserRole> getRolesByUserId(@PathVariable Integer userId) {
        return userRoleService.getRolesByUserId(userId);
    }


    @GetMapping("/role/{roleId}")
    public List<UserRole> getUsersByRoleId(@PathVariable Integer roleId) {
        return userRoleService.getUsersByRoleId(roleId);
    }


    @DeleteMapping("/{userId}/{roleId}")
    public void deleteUserRole(@PathVariable Integer userId, @PathVariable Integer roleId) {
        userRoleService.deleteUserRole(new UserRoleId(userId, roleId));
    }
}
