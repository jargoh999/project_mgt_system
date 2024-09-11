package com.jargoh.project_management_system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.AUTO;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @OneToOne
    private Project project;
    private String name;
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "chat", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Message> messages;
    @ManyToMany
    private List<User> users = new ArrayList<>();

}