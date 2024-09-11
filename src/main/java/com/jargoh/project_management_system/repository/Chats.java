package com.jargoh.project_management_system.repository;

import com.jargoh.project_management_system.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Chats extends JpaRepository<Chat, Long> {

}
