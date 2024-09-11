package com.jargoh.project_management_system.Controllers;

import com.jargoh.project_management_system.dtos.CreateCommentRequest;
import com.jargoh.project_management_system.model.Comment;
import com.jargoh.project_management_system.model.User;
import com.jargoh.project_management_system.services.impl.CommentServiceImpl;
import com.jargoh.project_management_system.services.impl.UserServicesImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@Controller
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
     private final CommentServiceImpl commentService;
     private final UserServicesImpl userServices;

     @PostMapping
    public ResponseEntity<?> createComment(
            @RequestBody CreateCommentRequest request,
            @RequestHeader("Authorization") String jwt
     ){
         try {
             User user = userServices.findUserProfileByJwt(jwt);
             Comment comment = commentService.createComment(request.getIssueId(), user.getId(), request.getContent());
             return new ResponseEntity<>(comment, OK);
         }catch(Exception e){
             return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);

         }
     }


    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(
            @PathVariable Long commentId,
            @RequestHeader("Authorization") String jwt
     ){try{
         User user = userServices.findUserProfileByJwt(jwt);
         commentService.deleteComment(commentId, user.getId());
         return new ResponseEntity<>("comment successfully deleted",OK);
    }catch(Exception e){
        return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);

    }
     }


    @GetMapping("/{issueId}")
    public ResponseEntity<?> getCommentByIssueId(@PathVariable Long issueId){
      try{
        return new ResponseEntity<>(commentService.findCommentByIssueId(issueId), OK);
    }catch(Exception e){
          return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);

    }
     }



}
