package com.examples.flywithus.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "orders")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "order_date")
    private Timestamp orderDate;
    
    @Column(name = "adult_tickets")
    private int adultTickets;
    
    @Column(name = "flight_id")
    private int flightId;
    
    public int getAdultTickets() {
        return adultTickets;
    }
    
    public Order() {
        
    }

    public Order(int orderId, String username, Timestamp orderDate, int adultTickets, int flightId) {
        super();
        this.orderId = orderId;
        this.username = username;
        this.orderDate = orderDate;
        this.adultTickets = adultTickets;
        this.flightId = flightId;
    }

    public void setAdultTickets(int adultTickets) {
        this.adultTickets = adultTickets;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }


}
