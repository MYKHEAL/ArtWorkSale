package org.example.dtos.Response;

import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.List;

@Data
@AllArgsConstructor
public class ProfileResponse {
    private String name;
    private String email;
    private List<ArtWorkResponse> artworks;
    private List<OrderResponse> orders;
}
