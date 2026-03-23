package com.org.smartwarehouse.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
    private String customerName;
    private String productName;
    private Integer quantity;
    private String status;
}