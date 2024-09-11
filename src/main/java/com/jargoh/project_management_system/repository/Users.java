package com.jargoh.project_management_system.repository;

import com.jargoh.project_management_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Users extends JpaRepository<User,Long> {
    User findUserByEmailEqualsIgnoreCase(String email);
}
