package org.example.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class ArtWork {
    @Id
    private String id;
    private String title;
    private String description;
    private String imageUrl;
    private String category;
    private double price;
    private String artistName;
    private boolean isAvailable;
    private String ownerEmail;
}
