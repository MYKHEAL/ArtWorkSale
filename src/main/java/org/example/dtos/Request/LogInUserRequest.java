package org.example.dtos.Request;

import lombok.Data;

@Data
public class LogInUserRequest {
    private String email;
    private String password;
}
