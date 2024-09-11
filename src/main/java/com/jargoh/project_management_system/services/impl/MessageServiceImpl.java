package com.jargoh.project_management_system.services.impl;

import com.jargoh.project_management_system.model.Chat;
import com.jargoh.project_management_system.model.Message;
import com.jargoh.project_management_system.model.User;
import com.jargoh.project_management_system.repository.Messages;
import com.jargoh.project_management_system.repository.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl {

    private final Messages messages;
    private final Users users;
    private final ProjectService projectService;
   public  Message sendMessage(Long senderId , Long projectId, String content){
       User sender = users.findById(senderId)
               .orElseThrow(()-> new RuntimeException("Not found"));
       Chat chat= projectService.getProjectById(projectId).getChat();
       Message message = new Message();
       message.setContent(content);
       message.setSender(sender);
       message.setChat(chat);
       message.setCreatedAt(now());
       message=messages.save(message);
       chat.getMessages().add(message);
       return message;
   }

   public List<Message> getMessagesByProjectId(Long projectId){
       Chat chat = projectService.getChatByProjectId(projectId);
       return messages.findByChatIdOrderByCreatedAtAsc(chat.getId());
   }
}

