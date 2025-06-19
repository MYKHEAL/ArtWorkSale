package org.example.dtos.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ArtWorkRequest {
    private String title;
    private String description;
    private String imageUrl;
    private String category;
    private double price;
    private String artistName;
    @JsonProperty("available")
    private boolean isAvailable;
    private String OwnerId;
}
