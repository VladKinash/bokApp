package org.libapp.libapp.repository;

import org.libapp.libapp.entity.UserRole;
import org.libapp.libapp.entity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepo extends JpaRepository<UserRole, UserRoleId> {
    List<UserRole> findByUserId(Integer userId);

    List<UserRole> findByRoleId(Integer roleId);
}
