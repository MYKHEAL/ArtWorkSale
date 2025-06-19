package org.example.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Data
@Document
public class Order {
    @Id
    private String id;
    private String artWorkId;
    private String buyerId;
    private LocalDateTime orderDate;
    private double payment;
    private String artistName;
    private String imageUrl;
}
