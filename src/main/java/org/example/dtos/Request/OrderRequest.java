package org.example.dtos.Request;

import lombok.Data;

@Data
public class OrderRequest {
    private String artWorkId;
    private String buyer;
    private double payment;

}
