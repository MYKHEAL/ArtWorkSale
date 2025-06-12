package org.example.repository;

import org.example.model.ArtWork;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ArtWorkRepository extends MongoRepository<ArtWork, String> {

}
