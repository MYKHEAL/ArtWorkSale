package org.example.services;

import org.example.dtos.Request.LogInUserRequest;
import org.example.dtos.Request.RegisterUserRequest;
import org.example.dtos.Response.LogInUserResponse;
import org.example.dtos.Response.RegisterUserResponse;
import org.example.model.Users;
import org.example.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    void testThaRegisterLogicWork() {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setEmail("test@test.com");
        registerUserRequest.setPassword("Password23");
        registerUserRequest.setPhone("12345678944");
        registerUserRequest.setName("JohnDoe");
        when(userRepository.findByEmail(registerUserRequest.getEmail())).thenReturn(null);
        when(userRepository.save(ArgumentMatchers.any(Users.class))).thenAnswer(i -> i.getArguments()[0]);

        RegisterUserResponse registerUserResponse = userService.registerUser(registerUserRequest);
        assertNotNull(registerUserResponse);
        assertEquals("Register Successfully", registerUserResponse.getMessage());

    }
    @Test
    void testRegisterWithEmptyName() {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setName("");
        request.setEmail("test@example.com");
        request.setPassword("Password123");
        request.setPhone("123456");

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.registerUser(request));
        assertEquals("Name cannot be empty", exception.getMessage());
    }

    @Test
    void testRegisterWithInvalidEmail() {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setName("Jane");
        request.setEmail("janeDoe");
        request.setPassword("Password123");
        request.setPhone("123456");

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.registerUser(request));
        assertEquals("Invalid email format", exception.getMessage());
    }

    @Test
    void testRegisterWithDuplicateEmail() {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setName("John");
        request.setEmail("test@example.com");
        request.setPassword("Password123");
        request.setPhone("12345678978");

        Users existingUser = new Users();
        existingUser.setEmail("test@example.com");

        when(userRepository.findByEmail(request.getEmail())).thenReturn(existingUser);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.registerUser(request));
        assertEquals("Email already registered", exception.getMessage());
    }


    @Test
    void testLoginWithValidCredentials() {
        LogInUserRequest request = new LogInUserRequest();
        request.setEmail("test@example.com");
        request.setPassword("password123");

        Users mockUser = new Users();
        mockUser.setId("1");
        mockUser.setEmail("test@example.com");
        mockUser.setPassword("password123");

        when(userRepository.findByEmail(request.getEmail())).thenReturn(mockUser);

        LogInUserResponse response = userService.logInUser(request);

        assertNotNull(response);
        assertEquals("Login successful", response.getMessage());
        assertEquals("1", response.getUserId());
    }


    @Test
    void testLoginWithNonExistentEmail() {
        LogInUserRequest request = new LogInUserRequest();
        request.setEmail("notfound@example.com");
        request.setPassword("password123");

        when(userRepository.findByEmail(request.getEmail())).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.logInUser(request));
        assertEquals("Invalid email or password", exception.getMessage());
    }

    @Test
    void testLoginWithWrongPassword() {
        LogInUserRequest request = new LogInUserRequest();
        request.setEmail("test@example.com");
        request.setPassword("wrongpass");

        Users mockUser = new Users();
        mockUser.setEmail("test@example.com");
        mockUser.setPassword("correctpass");

        when(userRepository.findByEmail(request.getEmail())).thenReturn(mockUser);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.logInUser(request));
        assertEquals("Invalid email or password", exception.getMessage());
    }



}