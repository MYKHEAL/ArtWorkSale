package org.example.services;

import org.example.dtos.Response.ProfileResponse;

public interface ProfileService {
    ProfileResponse getUserProfile(String userId);
}
