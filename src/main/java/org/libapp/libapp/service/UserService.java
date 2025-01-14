package org.libapp.libapp.service;

import org.libapp.libapp.entity.User;
import org.libapp.libapp.exception.ResourceNotFoundException;
import org.libapp.libapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User addUser(User user) {
        return userRepo.save(user);
    }

    public User getUserById(Integer id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public User updateUser(Integer id, User updatedUser) {
        User existingUser = getUserById(id);
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPasswordHash(updatedUser.getPasswordHash());
        existingUser.setProfilePic(updatedUser.getProfilePic());
        existingUser.setBio(updatedUser.getBio());
        return userRepo.save(existingUser);
    }

    public void deleteUser(Integer id) {
        User user = getUserById(id);
        userRepo.delete(user);
    }
}
