package com.jargoh.project_management_system.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogInResponse {
    private String email;
    private String jwtToken;
}
