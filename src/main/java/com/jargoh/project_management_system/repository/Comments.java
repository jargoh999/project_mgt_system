package com.jargoh.project_management_system.repository;

import com.jargoh.project_management_system.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Comments extends JpaRepository<Comment,Long> {
    @Query("select comment from Comment comment where comment.issue.id=:issueId")
    List<Comment> findCommentByIssueId(Long issueId);
}
