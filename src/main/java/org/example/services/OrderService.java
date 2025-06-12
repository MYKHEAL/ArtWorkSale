package org.example.services;

import org.example.dtos.Request.OrderRequest;
import org.example.dtos.Response.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest request);
    List<OrderResponse> getOrderByUser(String buyerId);
}
