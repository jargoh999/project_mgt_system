package com.jargoh.project_management_system.services.impl;

import com.jargoh.project_management_system.model.Comment;
import com.jargoh.project_management_system.model.User;
import com.jargoh.project_management_system.repository.Comments;
import com.jargoh.project_management_system.repository.Issues;
import com.jargoh.project_management_system.repository.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl {

    private final Comments comments;
    private final Issues issues;
    private final Users users;



    public Comment createComment(Long issueId, Long userId, String content ){
           Comment comment = new Comment();
           comment.setContent(content);
           comment.setIssue(issues.findById(issueId).orElseThrow(() -> new IllegalStateException("Issue not found")));
           comment.setUser(users.findById(userId).orElseThrow(() -> new IllegalStateException("User not found")));
           comment.setCreationTime(LocalDateTime.now());
           comment.getIssue().getComments().add(comment);
           return comments.save(comment);
    }


    public void deleteComment(Long commentId,Long userId) {
        Comment comment = comments.findById(commentId).orElseThrow(() -> new IllegalStateException("Comment not found"));
        User user = users.findById(userId).orElseThrow(() -> new IllegalStateException("User not found"));
        if(comment.getUser().equals(user))comments.delete(comment);
        else throw new RuntimeException("Comment not found");
    }

   public List<Comment> findCommentByIssueId(Long issueId){
        return comments.findCommentByIssueId(issueId);
   }

}
