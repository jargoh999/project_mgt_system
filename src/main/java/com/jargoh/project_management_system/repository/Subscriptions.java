package com.jargoh.project_management_system.repository;

import com.jargoh.project_management_system.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Subscriptions extends JpaRepository<Subscription,Long> {
    @Query("select sub from Subscription  sub where sub.user.id =: userId")
    Subscription findByUserId(Long userId);
}
