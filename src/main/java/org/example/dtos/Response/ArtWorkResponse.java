package org.example.dtos.Response;

import lombok.Data;

@Data
public class ArtWorkResponse {
    private String id;
    private String title;
    private String description;
    private String imageUrl;
    private String category;
    private double price;
    private String artistName;
    private boolean isAvailable;
}
