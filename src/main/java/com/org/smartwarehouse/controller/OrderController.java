package com.org.smartwarehouse.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.smartwarehouse.dto.OrderDto;
import com.org.smartwarehouse.model.Order;
import com.org.smartwarehouse.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> listOrders() {
        return orderService.listOrders();
    }

    @PostMapping
    public Order createOrder(@RequestBody OrderDto dto) {
    	   Order order = new Order();
    	    order.setCustomerName(dto.getCustomerName());
    	    order.setProductName(dto.getProductName());
    	    order.setQuantity(dto.getQuantity());
    	    order.setStatus(dto.getStatus());
        return orderService.createOrder(order);
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable("id") Long id) {
        return orderService.getOrderById(id);
    }


    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable("id") Long id, @RequestBody OrderDto dto) {
    	  Order updatedOrder = new Order();
    	  updatedOrder.setCustomerName(dto.getCustomerName());
    	  updatedOrder.setProductName(dto.getProductName());
    	  updatedOrder.setQuantity(dto.getQuantity());
    	  updatedOrder.setStatus(dto.getStatus());
    	
        return orderService.updateOrder(id, updatedOrder);
    }
    
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteOrder(id);
    }
}