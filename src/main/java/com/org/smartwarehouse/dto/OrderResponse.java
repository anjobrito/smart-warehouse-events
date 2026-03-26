package com.org.smartwarehouse.dto;


import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponse {
	private Long id;
    private String customerName;
    private String productName;
    private Integer quantity;
    private String status;
    private LocalDateTime createdAt;
}