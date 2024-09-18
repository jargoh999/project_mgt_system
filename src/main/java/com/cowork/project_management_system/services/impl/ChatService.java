package com.cowork.project_management_system.services.impl;

import com.cowork.project_management_system.model.Chat;
import com.cowork.project_management_system.repository.Chats;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final Chats chats;
    Chat createChat(Chat chat){
        return chats.save(chat);
    }

}
