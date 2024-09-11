package com.jargoh.project_management_system.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;
import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Data
@Table(name = "app_users")
public class User {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String fullName;
    private String email;
    @JsonProperty(access = WRITE_ONLY)
    private String password;
    private int projectSize;
}
