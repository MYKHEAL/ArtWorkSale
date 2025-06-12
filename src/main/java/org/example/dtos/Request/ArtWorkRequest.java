package org.example.dtos.Request;

import lombok.Data;

@Data
public class ArtWorkRequest {
    private String title;
    private String description;
    private String imageUrl;
    private String category;
    private double price;
    private String artistName;
    private boolean isAvailable;
}
