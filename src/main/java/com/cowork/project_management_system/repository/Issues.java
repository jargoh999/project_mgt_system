package com.cowork.project_management_system.repository;

import com.cowork.project_management_system.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Issues extends JpaRepository<Issue, Long> {
    @Query("select issue from Issue issue where issue.project.id =:id")
    List<Issue> findIssueByProjectId( Long id );
    @Query("select issue from Issue issue where issue.assignee.id=:userId")
    List<Issue> findIssuesByUserId( Long userId );


}
