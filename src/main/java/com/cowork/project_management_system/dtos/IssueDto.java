package com.cowork.project_management_system.dtos;

import com.cowork.project_management_system.model.Project;
import com.cowork.project_management_system.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueDto {
    private Long id;
    private User assignee;
    private Project project;
    private String title;
    private String description;
    private String status;
    private String priority;
    private LocalDate dueDate;
    private Long project_ID;
    private List<String> tags = new ArrayList<>();
}
