package com.jargoh.project_management_system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private User assignee;
    @JsonIgnore
    @ManyToOne
    private Project project;
    private String title;
    private String description;
    private String status;
    private String priority;
    private LocalDate dueDate;
    @Column(name = "project's_id")
    private Long project_ID;
    private List<String> tags = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "issue", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();


}

