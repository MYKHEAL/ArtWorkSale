package org.example.services;

import org.example.dtos.Request.ArtWorkRequest;
import org.example.dtos.Response.ArtWorkResponse;
import org.example.model.ArtWork;
import org.example.repository.ArtWorkRepository;
import org.example.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<ArtWorkResponse> getAllArtWork() {
        return artWorkRepository.findAll()
                .stream()
                .map(Mapper::mapToArtWorkResponse)
                .collect(Collectors.toList());

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
}
