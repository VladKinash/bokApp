package org.libapp.libapp.service;

import org.libapp.libapp.entity.Role;
import org.libapp.libapp.exception.ResourceNotFoundException;
import org.libapp.libapp.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepo roleRepo;

    @Autowired
    public RoleService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    public List<Role> getAllRoles() {
        return roleRepo.findAll();
    }

    public Role addRole(Role role) {
        return roleRepo.save(role);
    }

    public Role getRoleById(Integer id) {
        return roleRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + id));
    }

    public List<Role> getRolesByIds(List<Integer> ids){
        return roleRepo.findAllById(ids);
    }

    public Role updateRole(Integer id, Role updatedRole) {
        Role existingRole = getRoleById(id);
        existingRole.setName(updatedRole.getName());
        return roleRepo.save(existingRole);
    }

    public void deleteRole(Integer id) {
        Role role = getRoleById(id);
        roleRepo.delete(role);
    }
}
