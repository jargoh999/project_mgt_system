package com.cowork.project_management_system.dtos;

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
    private String status;
    private String priority;
    private LocalDate dueDate;
    private Long project_ID;
}
