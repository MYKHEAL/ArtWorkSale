package org.example.services;

import org.example.Validation.inputValidations;
import org.example.dtos.Request.LogInUserRequest;
import org.example.dtos.Request.RegisterUserRequest;
import org.example.dtos.Response.LogInUserResponse;
import org.example.dtos.Response.RegisterUserResponse;
import org.example.model.Users;
import org.example.repository.UserRepository;
import org.example.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
@Autowired
private UserRepository userRepository;

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest) {
        if (userRepository.findByEmail(registerUserRequest.getEmail()) != null) {
            throw new RuntimeException("Email already registered");
        }
        inputValidations.validateEmail(registerUserRequest.getEmail());
        inputValidations.validatePassword(registerUserRequest.getPassword());
        inputValidations.validateName(registerUserRequest.getName());
        inputValidations.validatePhone(registerUserRequest.getPhone());

        Users user = Mapper.mapToUser(registerUserRequest);
        Users savedUser = userRepository.save(user);
        return Mapper.mapToResponse(savedUser);

    }

    @Override
    public LogInUserResponse logInUser(LogInUserRequest request) {
        Users user = userRepository.findByEmail(request.getEmail());
        if(user == null || !user.getPassword().equals(request.getPassword())){
            throw new RuntimeException("Invalid email or password");
        }
        return Mapper.mapToLogIn(user);
    }
}
