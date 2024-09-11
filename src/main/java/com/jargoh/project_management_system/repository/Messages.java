package com.jargoh.project_management_system.repository;

import com.jargoh.project_management_system.model.Message;
import org.eclipse.angus.mail.imap.MessageCache;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Messages extends JpaRepository<Message,Long> {
    List<Message> findByChatIdOrderByCreatedAtAsc(Long id);
}
