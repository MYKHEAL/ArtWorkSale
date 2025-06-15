package org.example.utils;

import org.example.dtos.Request.ArtWorkRequest;
import org.example.dtos.Request.OrderRequest;
import org.example.dtos.Request.RegisterUserRequest;
import org.example.dtos.Response.ArtWorkResponse;
import org.example.dtos.Response.LogInUserResponse;
import org.example.dtos.Response.OrderResponse;
import org.example.dtos.Response.RegisterUserResponse;
import org.example.model.ArtWork;
import org.example.model.Order;
import org.example.model.Users;

import java.time.LocalDateTime;

public class Mapper {

    public static Users mapToUser(RegisterUserRequest request) {
        Users users = new Users();
        users.setName(request.getName());
        users.setEmail(request.getEmail());
        users.setPassword(request.getPassword());
        users.setPhone(request.getPhone());
        return users;

    }


    public static RegisterUserResponse mapToResponse(Users users) {
        RegisterUserResponse response = new RegisterUserResponse();
        response.setMessage("Register Successfully");
        response.setId(users.getId());
        return response;
    }



    public static LogInUserResponse mapToLogIn(Users users) {
        LogInUserResponse response = new LogInUserResponse();

        if (users == null) {
            response.setMessage("Login failed: user not found");
        } else {
            response.setMessage("Login successful");
            response.setUserId(users.getId());
        }
        return response;

    }

    public static ArtWork mapToArtWork(ArtWorkRequest request) {
        ArtWork artWork = new ArtWork();
        artWork.setTitle(request.getTitle());
        artWork.setDescription(request.getDescription());
        artWork.setImageUrl(request.getImageUrl());
        artWork.setPrice(request.getPrice());
        artWork.setCategory(request.getCategory());
        artWork.setAvailable(request.isAvailable());
        return artWork;
    }


    public static ArtWorkResponse mapToArtWorkResponse(ArtWork artWork) {
        ArtWorkResponse response = new ArtWorkResponse();
        response.setId(artWork.getId());
        response.setTitle(artWork.getTitle());
        response.setDescription(artWork.getDescription());
        response.setPrice(artWork.getPrice());
        response.setCategory(artWork.getCategory());
        response.setAvailable(artWork.isAvailable());
        response.setArtistName(artWork.getArtistName());
        response.setImageUrl(artWork.getImageUrl());
        return response;
    }


    public static Order mapToOrder(OrderRequest request) {
        Order order = new Order();
        order.setArtWorkId(request.getArtWorkId());
        order.setOrderDate(LocalDateTime.now());
        order.setPayment(request.getPayment());
        order.setBuyerId(request.getBuyer());
        return order;
    }


    public static OrderResponse mapToOrderResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getArtWorkId());
        response.setPayment(order.getPayment());
        response.setBuyerId(order.getBuyerId());
        response.setArtWorkId(order.getArtWorkId());
        response.setOrderDate(order.getOrderDate());
        return response;

    }

}
