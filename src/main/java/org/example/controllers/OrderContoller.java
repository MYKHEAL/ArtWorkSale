package org.example.controllers;

import org.example.dtos.Request.OrderRequest;
import org.example.dtos.Response.OrderResponse;
import org.example.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderContoller {
    @Autowired
    OrderService orderService;

    @PostMapping
    public OrderResponse placeOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.placeOrder(orderRequest);
    }

    @GetMapping("/{buyerId}")
    public List<OrderResponse> getOrdersByBuyerId(@PathVariable String buyerId) {
        return orderService.getOrderByUser(buyerId);
    }



}
