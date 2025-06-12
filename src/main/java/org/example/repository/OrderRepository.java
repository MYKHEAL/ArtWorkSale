package org.example.repository;

import org.example.model.ArtWork;
import org.example.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<ArtWork> findByArtistName(String artistName);
}
