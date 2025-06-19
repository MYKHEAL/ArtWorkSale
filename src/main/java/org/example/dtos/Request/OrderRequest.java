package org.example.dtos.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderRequest {
    @JsonProperty("artworkId")
    private String artWorkId;
    private String buyer;
    private double payment;

}
