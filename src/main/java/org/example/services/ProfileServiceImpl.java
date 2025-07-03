package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.dtos.Response.ProfileResponse;
import org.example.model.ArtWork;
import org.example.model.Order;
import org.example.model.Users;
import org.example.repository.ArtWorkRepository;
import org.example.repository.OrderRepository;
import org.example.repository.UserRepository;
import org.example.utils.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProfileServiceImpl implements ProfileService {
    private final OrderRepository orderRepository;
    private final ArtWorkRepository artWorkRepository;
    private final UserRepository userRepository;




    @Override
    public ProfileResponse getUserProfile(String userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<ArtWork> userArtWork = artWorkRepository.findByOwnerId(userId);
        List<Order> userOrders = orderRepository.findByBuyerId(userId);

        return new ProfileResponse(
                user.getName(),
                user.getEmail(),
                userArtWork.stream().map(Mapper::mapToArtWorkResponse).collect(Collectors.toList()),
                userOrders.stream().map(Mapper::mapToOrderResponse).collect(Collectors.toList())
        );
    }


}
