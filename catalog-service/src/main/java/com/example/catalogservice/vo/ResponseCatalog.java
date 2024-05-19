package com.example.catalogservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseCatalog {


    private String productId;
    private Integer productName;
    private Integer unitPrice;
    private Integer stock;
    private LocalDateTime createAt;

}