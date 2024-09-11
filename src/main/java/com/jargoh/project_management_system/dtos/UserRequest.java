package com.jargoh.project_management_system.dtos;

import com.jargoh.project_management_system.model.Issue;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class UserRequest {
    private String fullName;
    private String email;
    private String password;
}
