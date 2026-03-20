package com.org.smartwarehouse.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.org.smartwarehouse.model.Order;


public interface OrderRepository extends JpaRepository<Order, Long> {

}