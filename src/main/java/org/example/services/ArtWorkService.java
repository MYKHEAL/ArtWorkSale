package org.example.services;

import org.example.dtos.Request.ArtWorkRequest;
import org.example.dtos.Response.ArtWorkResponse;

import java.util.List;

public interface ArtWorkService {
    ArtWorkResponse createArtWork(ArtWorkRequest request);
    List<ArtWorkResponse> getAllArtWork();
    ArtWorkResponse getArtWorkById(String artWorkId);
    void deleteArtWorkById(String artWorkId);
}
