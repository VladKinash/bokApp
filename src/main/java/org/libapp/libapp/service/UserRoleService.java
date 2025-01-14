package org.libapp.libapp.service;

import org.libapp.libapp.entity.UserRole;
import org.libapp.libapp.entity.UserRoleId;
import org.libapp.libapp.exception.ResourceNotFoundException;
import org.libapp.libapp.repository.UserRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {

    private final UserRoleRepo userRoleRepo;

    @Autowired
    public UserRoleService(UserRoleRepo userRoleRepo) {
        this.userRoleRepo = userRoleRepo;
    }

    public List<UserRole> getAllUserRoles() {
        return userRoleRepo.findAll();
    }

    public UserRole addUserRole(UserRole userRole) {
        return userRoleRepo.save(userRole);
    }

    public UserRole getUserRoleById(UserRoleId id) {
        return userRoleRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserRole not found with id: " + id));
    }

    public List<UserRole> getRolesByUserId(Integer userId) {
        return userRoleRepo.findByUserId(userId);
    }

    public List<UserRole> getUsersByRoleId(Integer roleId) {
        return userRoleRepo.findByRoleId(roleId);
    }

    public void deleteUserRole(UserRoleId id) {
        UserRole userRole = getUserRoleById(id);
        userRoleRepo.delete(userRole);
    }
}
