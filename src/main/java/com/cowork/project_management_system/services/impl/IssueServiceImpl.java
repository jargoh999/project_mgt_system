package com.cowork.project_management_system.services.impl;

import com.cowork.project_management_system.dtos.IssueRequest;
import com.cowork.project_management_system.model.Issue;
import com.cowork.project_management_system.model.Project;
import com.cowork.project_management_system.model.Status;
import com.cowork.project_management_system.model.User;
import com.cowork.project_management_system.repository.Issues;
import com.cowork.project_management_system.repository.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IssueServiceImpl {

    private final Issues issues;

    private final ProjectService projectService;

    private final UserServicesImpl userServices;
    private final Users users;

    public Issue getIssueById(Long issueId) {
        return issues.findById(issueId).orElseThrow(() -> new IllegalStateException("something is wrong"));
    }

    public Issue createIssue(IssueRequest issueRequest, Long userId) {
        Project project = projectService.getProjectById(issueRequest.getProject_ID());
        Issue issue = new Issue();
        issue.setTitle(issueRequest.getTitle());
        issue.setDescription(issueRequest.getDescription());
        issue.setStatus(issueRequest.getStatus());
        issue.setPriority(issueRequest.getPriority());
        issue.setDueDate(issueRequest.getDueDate());
        issue.setProject_ID(issueRequest.getProject_ID());
        issue.setProject(project);
        return issues.save(issue);
    }

    public Issue updateIssue(IssueRequest issueRequest, Long userId, Long issueId) {
        Issue issue = getIssueById(issueId);
        issue.setStatus(issueRequest.getStatus());
        return issues.save(issue);
    }

    public void deleteIssue(Long issueId, Long userId) {
        getIssueById(issueId);
        issues.deleteById(issueId);
    }

    public List<Issue> getIssueByProjectId(Long projectId) {
        return issues.findIssueByProjectId(projectId);
    }

    public List<Issue> searchIssues(String title, String status, String priority, Long assigneeId) {
        return null;
    }

    public List<User> getAssigneeForIssue(Long issueId) {
        return null;
    }

    public Issue addUserToIssue(Long issueId, Long userId) {
        User user = userServices.findUserByUserId(userId);
        Issue issue = getIssueById(issueId);
        issue.setAssignee(user);
        return issues.save(issue);

    }


    public Issue updateStatus(Long issueId, String status) {
        Issue issue = getIssueById(issueId);
        issue.setStatus(Status.valueOf(status));
        return issues.save(issue);
    }

    public List<Issue> getAllUserIssues(Long userId) {
        return issues.findIssuesByUserId(userId);
    }
}