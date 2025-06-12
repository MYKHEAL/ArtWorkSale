package org.example.services;

import org.example.dtos.Request.LogInUserRequest;
import org.example.dtos.Request.RegisterUserRequest;
import org.example.dtos.Response.LogInUserResponse;
import org.example.dtos.Response.RegisterUserResponse;

public interface UserService {
    RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest);

    LogInUserResponse logInUser(LogInUserRequest logInUserRequest);
}
