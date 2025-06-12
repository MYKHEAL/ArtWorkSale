package org.example.services;

import org.example.dtos.Request.OrderRequest;
import org.example.dtos.Response.OrderResponse;
import org.example.model.ArtWork;
import org.example.model.Order;
import org.example.repository.ArtWorkRepository;
import org.example.repository.OrderRepository;
import org.example.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ArtWorkRepository artWorkRepository;

    @Override
    public OrderResponse placeOrder(OrderRequest request) {
        ArtWork artWork = artWorkRepository.findById(request.getArtWorkId())
                .orElseThrow(() -> new RuntimeException("ArtWork not found"));

        if(!artWork.isAvailable()){
            throw new RuntimeException("ArtWork is not available");
        }

        artWork.setAvailable(false);
        artWorkRepository.save(artWork);

        Order order = Mapper.mapToOrder(request);
        Order savedOrder = orderRepository.save(order);
        return Mapper.mapToOrderResponse(savedOrder);

    }

    @Override
    public List<OrderResponse> getOrderByUser(String buyerId) {
        return orderRepository.findAll().stream()
                .filter(order -> order.getBuyerId().equals(buyerId))
                .map(Mapper::mapToOrderResponse)
                .collect(Collectors.toList());
    }
}
