package com.examples.flywithus.entity;

public class FullFlegedOrder extends Order {

    String fromAirport;

    String toAirport;

    public FullFlegedOrder(Order order, String fromLocation, String toLocation) {
        super(order.getOrderId(), order.getUsername(), order.getOrderDate(), order.getAdultTickets(),
                order.getFlightId());
        this.fromAirport = fromLocation;
        this.toAirport = toLocation;
    }

    public String getFromAirport() {
        return fromAirport;
    }

    public void setFromAirport(String fromAirport) {
        this.fromAirport = fromAirport;
    }

    public String getToAirport() {
        return toAirport;
    }

    public void setToAirport(String toAirport) {
        this.toAirport = toAirport;
    }

    

}
