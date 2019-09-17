package com.examples.flywithus.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.examples.flywithus.entity.FullFlegedOrder;
import com.examples.flywithus.entity.Order;

public interface IOrderService {

    @Secured ({"ROLE_ADMIN", "ROLE_USER"})
    Order placeOrder(Order order);

    @Secured ({"ROLE_ADMIN", "ROLE_USER"})
    Order getOrder(int orderId);

    @Secured ({"ROLE_ADMIN", "ROLE_USER"})
    List<FullFlegedOrder> getAllOrders(String username);

    @Secured ({"ROLE_ADMIN", "ROLE_USER"})
    boolean deleteOrder(int orderId);

}