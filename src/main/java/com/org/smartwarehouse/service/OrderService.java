package com.org.smartwarehouse.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.org.smartwarehouse.model.Order;
import com.org.smartwarehouse.producer.OrderEventProducer;
import com.org.smartwarehouse.dto.OrderResponse;
import com.org.smartwarehouse.dto.event.OrderCreatedEvent;
import com.org.smartwarehouse.dto.OrderRequest;
import com.org.smartwarehouse.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderEventProducer orderEventProducer;

    public OrderService(OrderRepository orderRepository, OrderEventProducer orderEventProducer) {
        this.orderRepository = orderRepository;
        this.orderEventProducer = orderEventProducer;
        
    }
    
    public List<OrderResponse> listOrders() {
        return orderRepository.findAll()
        		.stream()
        		.map(this::toResponse)
        		.collect(Collectors.toList());
    }

    public OrderResponse createOrder(@RequestBody OrderRequest request) {
    	//Populate the model
    	Order order = new Order();    	
    	order.setCustomerName(request.getCustomerName());
    	order.setProductName(request.getProductName());
    	order.setQuantity(request.getQuantity());
    	order.setStatus(request.getStatus());    	    	
        order.setCreatedAt(LocalDateTime.now());
        //Persist
        Order savedOrder = orderRepository.save(order);
        //Retrieve the persisted data
        OrderCreatedEvent event = OrderCreatedEvent.builder()   
        		.orderId(savedOrder.getId())
                .customerName(savedOrder.getCustomerName())
                .productName(savedOrder.getProductName())
                .quantity(savedOrder.getQuantity())
                .status(savedOrder.getStatus())
                .createdAt(savedOrder.getCreatedAt())
                .build();
        //RabbitMQ Queue
        orderEventProducer.sendOrderCreatedEvent(event);
        //send response
        return toResponse(savedOrder);
    }

    public OrderResponse getOrderById(@PathVariable("id") Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(this::toResponse).orElse(null);
    }

    public OrderResponse updateOrder(@PathVariable("id") Long id, OrderRequest request) {
        Optional<Order> existing = orderRepository.findById(id);

        if (existing.isPresent()) {
            Order order = existing.get();
            order.setCustomerName(request.getCustomerName());
            order.setProductName(request.getProductName());
            order.setQuantity(request.getQuantity());
            order.setStatus(request.getStatus());
            
            Order updatedOrder = orderRepository.save(order);
            return toResponse(updatedOrder);
        }

        return null;
    }
    
    public void deleteOrder(Long id) {    	    	    	
        orderRepository.deleteById(id);
    }
    
    private OrderResponse toResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setCustomerName(order.getCustomerName());
        response.setProductName(order.getProductName());
        response.setQuantity(order.getQuantity());
        response.setStatus(order.getStatus());
        response.setCreatedAt(order.getCreatedAt());
        return response;
    }
}