package org.example.services;

import org.example.Validation.inputValidations;
import org.example.dtos.Request.ArtWorkRequest;
import org.example.dtos.Response.ArtWorkResponse;
import org.example.exceptions.UnauthorizedArtworkAccessException;
import org.example.model.ArtWork;
import org.example.repository.ArtWorkRepository;
import org.example.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ArtWorkServiceImpl implements ArtWorkService {
    @Autowired
    private ArtWorkRepository artWorkRepository;

    @Override
    public ArtWorkResponse createArtWork(ArtWorkRequest request) {
        ArtWork artwork = Mapper.mapToArtWork(request);
        ArtWork savedArtwork = artWorkRepository.save(artwork);
        return Mapper.mapToArtWorkResponse(savedArtwork);

    }

    @Override
    public ArtWorkResponse getArtWorkById(String artWorkId) {
        ArtWork artwork = artWorkRepository.findById(artWorkId)
                .orElseThrow(()-> new RuntimeException("Art work not found"));
        return Mapper.mapToArtWorkResponse(artwork);

    }


    @Override
    public void deleteArtWorkById(String id, String userEmail) {
        ArtWork artwork = artWorkRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Art work not found"));

        if (!artwork.getArtistName().equalsIgnoreCase(userEmail)) {
            throw new UnauthorizedArtworkAccessException("You are not allowed to delete this artwork");
        }

        artWorkRepository.deleteById(id);
    }

    @Override
    public List<ArtWorkResponse> getAllArtWorks() {
        List<ArtWork> artworks = artWorkRepository.findAll();
        List<ArtWorkResponse> responses = new ArrayList<>();

        for (ArtWork artwork : artworks) {
            ArtWorkResponse response = Mapper.mapToArtWorkResponse(artwork);
            responses.add(response);
        }

        return responses;
    }
    @Override
    public List<ArtWorkResponse> searchByKeyword(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        List<ArtWorkResponse> result = new ArrayList<>();

        for (ArtWork artwork : artWorkRepository.findAll()) {
            String title = artwork.getTitle() != null ? artwork.getTitle().toLowerCase() : "";
            String category = artwork.getCategory() != null ? artwork.getCategory().toLowerCase() : "";
            String artist = artwork.getArtistName() != null ? artwork.getArtistName().toLowerCase() : "";

            if (title.contains(lowerKeyword) || category.contains(lowerKeyword) || artist.contains(lowerKeyword)) {
                result.add(Mapper.mapToArtWorkResponse(artwork));
            }
        }

        return result;
    }


}



