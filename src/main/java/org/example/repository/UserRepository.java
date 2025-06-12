package org.example.repository;

import org.example.model.Users;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository  extends MongoRepository<Users, String> {
    Users findByEmail(String email);
}
