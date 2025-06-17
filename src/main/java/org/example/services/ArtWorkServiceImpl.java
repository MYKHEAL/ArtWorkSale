package org.example.services;

import org.example.dtos.Request.ArtWorkRequest;
import org.example.dtos.Response.ArtWorkResponse;
import org.example.model.ArtWork;
import org.example.repository.ArtWorkRepository;
import org.example.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void deleteArtWorkById(String artWorkId) {
        artWorkRepository.deleteById(artWorkId);
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
        List<ArtWorkResponse> result = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();

        for (ArtWork artwork : artWorkRepository.findAll()) {
            String title = artwork.getTitle().toLowerCase();
            String category = artwork.getCategory().toLowerCase();
            String artist = artwork.getArtistName().toLowerCase();

            if (title.contains(lowerKeyword) || category.contains(lowerKeyword) || artist.contains(lowerKeyword)) {
                result.add(Mapper.mapToArtWorkResponse(artwork));
            }
        }

        return result;
    }


}
