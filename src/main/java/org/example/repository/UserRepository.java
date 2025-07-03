package org.example.repository;

import org.example.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository  extends MongoRepository<Users, String> {
    Users findByEmail(String email);
    Optional<Users> findById(String id);
}
