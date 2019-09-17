package com.examples.flywithus.dao;

import java.util.List;

import com.examples.flywithus.entity.Order;

public interface IOrderDao {

    void placeOrder(Order order);

    Order getOrder(int orderId);
    
    List<Order> getAllOrders(String username);
    
    int deleteOrder(int orderId);
}
