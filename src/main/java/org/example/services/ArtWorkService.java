package org.example.services;

import org.example.dtos.Request.ArtWorkRequest;
import org.example.dtos.Response.ArtWorkResponse;
import org.example.model.ArtWork;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArtWorkService {
    ArtWorkResponse createArtWork(ArtWorkRequest request, String userId);
    ArtWorkResponse getArtWorkById(String artWorkId);
    void deleteArtWorkById(String artWorkId, String userEmail);
    List<ArtWorkResponse> getAllArtWorks();
    List<ArtWorkResponse> searchByKeyword(@Param("keyword") String keyword);

}
