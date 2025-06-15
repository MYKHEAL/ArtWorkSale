package org.example.controllers;

import org.example.dtos.Request.ArtWorkRequest;
import org.example.dtos.Response.ArtWorkResponse;
import org.example.services.ArtWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



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


    @GetMapping("/{id}")
    public ResponseEntity<?> getArtworkById(@PathVariable String id) {
        try {
            ArtWorkResponse response = artWorkService.getArtWorkById(id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        try {
            artWorkService.deleteArtWorkById(id);
            return ResponseEntity.ok("Artwork deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error deleting artwork: " + e.getMessage());
        }

    }
}