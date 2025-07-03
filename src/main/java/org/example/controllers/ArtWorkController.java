package org.example.controllers;

import org.example.dtos.Request.ArtWorkRequest;
import org.example.dtos.Response.ArtWorkResponse;
import org.example.exceptions.UnauthorizedArtworkAccessException;
import org.example.services.ArtWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/artworks")
public class ArtWorkController {

    @Autowired
    private ArtWorkService artWorkService;

    @PostMapping
    public ResponseEntity<?> createArtwork(@RequestBody ArtWorkRequest request) {
        try {
            ArtWorkResponse response = artWorkService.createArtWork(request, request.getOwnerId());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getArtworkById(@PathVariable("id") String id) {
        try {
            ArtWorkResponse response = artWorkService.getArtWorkById(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }


    @GetMapping
    public ResponseEntity<?> getAllArtworks() {
        try {
            List<ArtWorkResponse> artworks = artWorkService.getAllArtWorks();
            return ResponseEntity.ok(artworks);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to fetch artworks: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id, @RequestHeader("X-User-Email") String userEmail) {
        try {
            artWorkService.deleteArtWorkById(id, userEmail);
            return ResponseEntity.ok("Artwork deleted successfully");
        } catch (UnauthorizedArtworkAccessException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error deleting artwork: " + e.getMessage());
        }
    }


    @GetMapping("/search")
    public ResponseEntity<?> searchArtworks(@RequestParam("keyword") String keyword) {
        try {
            List<ArtWorkResponse> results = artWorkService.searchByKeyword(keyword);
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Search failed: " + e.getMessage());
        }
    }

}
