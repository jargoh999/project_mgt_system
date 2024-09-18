package com.cowork.project_management_system.repository;

import com.cowork.project_management_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Users extends JpaRepository<User,Long> {
    User findUserByEmailEqualsIgnoreCase(String email);
}
