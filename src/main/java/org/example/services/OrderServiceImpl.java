package org.example.services;

import org.example.dtos.Request.OrderRequest;
import org.example.dtos.Response.OrderResponse;
import org.example.model.ArtWork;
import org.example.model.Order;
import org.example.repository.ArtWorkRepository;
import org.example.repository.OrderRepository;
import org.example.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ArtWorkRepository artWorkRepository;

    @Override
    public OrderResponse placeOrder(OrderRequest request) {
        ArtWork artWork = artWorkRepository.findById(request.getArtWorkId())
                .orElseThrow(() -> new RuntimeException("Artwork not found"));

        if (!artWork.isAvailable()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This artwork has already been sold.");
        }

        if (request.getPayment() < artWork.getPrice()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient payment. Required: ₦" + artWork.getPrice());
        }

        if (request.getBuyer().equals(artWork.getOwnerId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You cannot buy your own artwork.");
        }

        artWork.setAvailable(false);
        artWorkRepository.save(artWork);

        Order order = Mapper.mapToOrder(request);
        order.setImageUrl(artWork.getImageUrl());
        Order savedOrder = orderRepository.save(order);

        return Mapper.mapToOrderResponse(savedOrder);
    }


    @Override
    public List<OrderResponse> getOrderByUser(String buyerId) {
        List<Order> orders = orderRepository.findAll();
        List<OrderResponse> responses = new ArrayList<>();
        for (Order order : orders) {
            if (order.getBuyerId().equals(buyerId)) {
                responses.add(Mapper.mapToOrderResponse(order));
            }
        }
        return responses;
    }
}