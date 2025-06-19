package org.example.dtos.Response;


import lombok.Data;

import java.time.LocalDateTime;
@Data
public class OrderResponse {
    private String artWorkId;
    private String buyerId;
    private LocalDateTime orderDate;
    private String payment;
    private String imageUrl;
}
