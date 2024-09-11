package com.jargoh.project_management_system.services.impl;

import com.jargoh.project_management_system.model.Chat;
import com.jargoh.project_management_system.repository.Chats;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final Chats chats;
    Chat createChat(Chat chat){
        return chats.save(chat);
    }

}
