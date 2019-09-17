package com.examples.flywithus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.examples.flywithus.entity.FullFlegedOrder;
import com.examples.flywithus.entity.Order;
import com.examples.flywithus.service.IOrderService;

@Controller
@CrossOrigin
@RequestMapping("home")
public class OrderController {

    @Autowired
    private IOrderService orderService;

//    @GetMapping("order/{id}")
    private ResponseEntity<Order> getOrderById(@PathVariable("id") Integer id) {
        Order order = orderService.getOrder(id);
        if(order != null)
            return new ResponseEntity<Order>(order, HttpStatus.OK);
        else
            return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("order/{username}")
    public ResponseEntity<List<FullFlegedOrder>> getAllOrder(@PathVariable("username") String username) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        if(!name.equals(username)) {
            return new ResponseEntity<List<FullFlegedOrder>>(HttpStatus.FORBIDDEN);
        }
        List<FullFlegedOrder> airports = orderService.getAllOrders(username);
        return new ResponseEntity<List<FullFlegedOrder>>(airports, HttpStatus.OK);
        
    }

    @PostMapping("order")
    public ResponseEntity<Order> addOrder(@RequestBody Order order, UriComponentsBuilder builder) {
        Order createdOrder = orderService.placeOrder(order);
        if (createdOrder == null) {
            return new ResponseEntity<Order>(HttpStatus.CONFLICT);
        }
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(builder.path("/order/{id}").buildAndExpand(order.getOrderId()).toUri());
        return new ResponseEntity<Order>(createdOrder, HttpStatus.CREATED);
    }
    
    @DeleteMapping("order/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") Integer id) {
        boolean result = orderService.deleteOrder(id);
        if(result == false) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }   

}
