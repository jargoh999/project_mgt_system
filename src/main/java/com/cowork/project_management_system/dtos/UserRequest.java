package com.cowork.project_management_system.dtos;

import lombok.Data;

@Data
public class UserRequest {
    private String fullName;
    private String email;
    private String password;
}
