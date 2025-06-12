package org.example.controllers;

import org.example.dtos.Request.ArtWorkRequest;
import org.example.dtos.Response.ArtWorkResponse;
import org.example.services.ArtWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artworks")
public class ArtWorkController {

    @Autowired
    private ArtWorkService artWorkService;

    @PostMapping
    public ResponseEntity<?> createArtwork(@RequestBody ArtWorkRequest request) {
        try {
            ArtWorkResponse response = artWorkService.createArtWork(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }



    @GetMapping
    public List<ArtWorkResponse> getAllArtWorks(){
        return artWorkService.getAllArtWork();
    }

    @GetMapping("/{id}")
    public ArtWorkResponse getArtWorkById(@PathVariable("id") String id){
        return artWorkService.getArtWorkById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        artWorkService.deleteArtWorkById(id);
    }
}
