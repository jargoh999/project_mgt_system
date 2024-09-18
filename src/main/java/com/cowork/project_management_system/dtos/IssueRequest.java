package com.cowork.project_management_system.dtos;

import com.cowork.project_management_system.model.Priority;
import com.cowork.project_management_system.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueRequest {
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private LocalDate dueDate;
    private Long project_ID;
}
