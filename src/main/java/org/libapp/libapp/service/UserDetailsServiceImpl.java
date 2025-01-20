package org.libapp.libapp.service;

import org.libapp.libapp.entity.User;
import org.libapp.libapp.entity.UserRole;
import org.libapp.libapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;
    private final UserRoleService userRoleService;

    @Autowired
    public UserDetailsServiceImpl(UserRepo userRepo, UserRoleService userRoleService) {
        this.userRepo = userRepo;
        this.userRoleService = userRoleService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        List<UserRole> userRoles = userRoleService.getRolesByUserId(user.getId());

        //so it will grant authority to each role by creating a new SimpleGrantedAuthority objects
        //which will store ROLE_ + the name of the role

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserRole ur : userRoles) {
            String roleName = ur.getRole().getName(); // for now 3: "ADMIN", "LIBRARIAN", "USER"
            authorities.add(new SimpleGrantedAuthority("ROLE_" + roleName));
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPasswordHash(),
                authorities
        );
    }
}
