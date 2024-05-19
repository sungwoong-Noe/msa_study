package com.example.orderservice.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ResponseOrder implements Serializable {

    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private String orderId;
    private String userId;

    private LocalDateTime createAt;
    

}
