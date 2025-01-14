package org.libapp.libapp.controller;

import org.libapp.libapp.entity.Role;
import org.libapp.libapp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }


    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }


    @PostMapping
    public Role createRole(@RequestBody Role role) {
        return roleService.addRole(role);
    }

    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable Integer id) {
        return roleService.getRoleById(id);
    }


    @PutMapping("/{id}")
    public Role updateRole(@PathVariable Integer id, @RequestBody Role updatedRole) {
        return roleService.updateRole(id, updatedRole);
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable Integer id) {
        roleService.deleteRole(id);
    }
}
