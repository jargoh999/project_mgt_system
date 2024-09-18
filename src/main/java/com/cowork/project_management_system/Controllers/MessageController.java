package com.cowork.project_management_system.Controllers;

import com.cowork.project_management_system.dtos.CreateMessageRequest;
import com.cowork.project_management_system.services.impl.MessageServiceImpl;
import com.cowork.project_management_system.model.Chat;
import com.cowork.project_management_system.model.Message;
import com.cowork.project_management_system.model.User;
import com.cowork.project_management_system.services.impl.ProjectService;
import com.cowork.project_management_system.services.impl.UserServicesImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@Controller
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageServiceImpl messageService;
    private final UserServicesImpl userServices;
    private final ProjectService projectService;

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody CreateMessageRequest createMessageRequest){
       try{
        User user = userServices.findUserByUserId(createMessageRequest.getSenderId());
        if(user == null) throw new RuntimeException("User not found");
        Chat chats = projectService.getProjectById(createMessageRequest.getProjectId()).getChat();
        if(chats==null)throw new RuntimeException("chats not found");
        Message message = messageService.sendMessage(createMessageRequest.getSenderId(), createMessageRequest.getProjectId(), createMessageRequest.getContent());
        return new ResponseEntity<>(message,OK);
       }catch(Exception e){
           return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);

       }
    }

    @GetMapping("/chat/{projectId}")
    public ResponseEntity<?> getMessagesByProjectId( @PathVariable Long projectId){
    try{
        return new ResponseEntity<>(messageService.getMessagesByProjectId(projectId), OK);
    }catch(Exception e){
        return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);
    }
    }

    @GetMapping("/chat/{chatId}")
    public ResponseEntity<?> getMessagesByChatId( @PathVariable Long chatId){
        try{
            return new ResponseEntity<>(messageService.getMessagesByChatId(chatId), OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);
        }
    }

}
