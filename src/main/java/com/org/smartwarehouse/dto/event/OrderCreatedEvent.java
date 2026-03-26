package com.org.smartwarehouse.dto.event;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderCreatedEvent {

    private Long orderId;
    private String customerName;
    private String productName;
    private Integer quantity;
    private String status;
    private LocalDateTime createdAt;
}