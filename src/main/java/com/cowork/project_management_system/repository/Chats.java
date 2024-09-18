package com.cowork.project_management_system.repository;

import com.cowork.project_management_system.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Chats extends JpaRepository<Chat, Long> {

}
