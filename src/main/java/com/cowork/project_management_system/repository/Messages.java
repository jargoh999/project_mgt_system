package com.cowork.project_management_system.repository;

import com.cowork.project_management_system.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Messages extends JpaRepository<Message,Long> {
    List<Message> findByChatIdOrderByCreatedAtAsc(Long id);
}
