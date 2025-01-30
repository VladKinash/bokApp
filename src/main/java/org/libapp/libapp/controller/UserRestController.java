package org.libapp.libapp.controller;

import org.libapp.libapp.entity.*;
import org.libapp.libapp.service.BorrowedBookService;
import org.libapp.libapp.service.RoleService;
import org.libapp.libapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }


    @PostMapping("/delete/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_LIBRARIAN"})
    @Transactional
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        User user = userService.getUserById(id);
        if (user != null) {
            user.clearRoles();
            userService.deleteUser(id);
            return ResponseEntity.ok("User deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @PostMapping("/update-roles/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_LIBRARIAN"})
    @Transactional
    public ResponseEntity<String> updateUserRoles(
            @PathVariable Integer id,
            @RequestParam(value = "roles", required = false) List<Integer> roleIds) {

        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        Set<UserRole> rolesToRemove = new HashSet<>();
        for (UserRole existingRole : user.getUserRoles()) {
            if (roleIds == null || !roleIds.contains(existingRole.getRole().getId())) {
                rolesToRemove.add(existingRole);
            }
        }
        user.getUserRoles().removeAll(rolesToRemove);

        if (roleIds != null) {
            for (Integer roleId : roleIds) {
                boolean roleExists = user.getUserRoles().stream().anyMatch(ur -> ur.getRole().getId().equals(roleId));
                if(!roleExists){
                    Role role = roleService.getRoleById(roleId);
                    if (role != null) {
                        UserRoleId userRoleId = new UserRoleId(user.getId(), role.getId());
                        UserRole userRole = new UserRole();
                        userRole.setId(userRoleId);
                        userRole.setUser(user);
                        userRole.setRole(role);
                        user.getUserRoles().add(userRole);
                    }
                }
            }
        }

        userService.updateUser(user.getId(), user);
        return ResponseEntity.ok("Roles updated successfully");
    }
}
