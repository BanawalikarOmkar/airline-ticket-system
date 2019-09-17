package com.examples.flywithus.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examples.flywithus.dao.IOrderDao;
import com.examples.flywithus.entity.Flight;
import com.examples.flywithus.entity.FullFlegedOrder;
import com.examples.flywithus.entity.Order;

import javassist.compiler.ast.NewExpr;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private IOrderDao orderDao;

    @Autowired
    private FlightService flightService;

    /*
     * (non-Javadoc)
     * 
     * @see com.examples.flywithus.service.IOrderService#placeOrder(com.examples.
     * flywithus.entity.Order)
     */
    @Override
    public Order placeOrder(Order order) {
        int tickets = order.getAdultTickets();
        Flight flight = flightService.getFlightById(order.getFlightId());
        int avaiableTicekts = flight.getAvailableAdultTickets();
        if (avaiableTicekts >= tickets) {
            orderDao.placeOrder(order);
            flight.setAvailableAdultTickets(avaiableTicekts - tickets);
            flightService.updateFlight(flight);
            return order;
        } else {
            return null;
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.examples.flywithus.service.IOrderService#getOrder(int)
     */
    @Override
    public Order getOrder(int orderId) {
        return orderDao.getOrder(orderId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.examples.flywithus.service.IOrderService#getAllOrders(java.lang.String)
     */
    @Override
    public List<FullFlegedOrder> getAllOrders(String username) {
        List<FullFlegedOrder> flegedOrders = new ArrayList<FullFlegedOrder>();
        
        for (Order order : orderDao.getAllOrders(username)) {
            Flight flight = flightService.getFlightById(order.getFlightId());
            flegedOrders.add(new FullFlegedOrder(order, flight.getToAirport(), flight.getFromAirport()));
        }
        return flegedOrders;

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.examples.flywithus.service.IOrderService#deleteOrder(int)
     */
    @Override
    public boolean deleteOrder(int orderId) {
        Order order = getOrder(orderId);
        if (order != null) {
            int ticketsFreed = order.getAdultTickets();
            Flight flight = flightService.getFlightById(order.getFlightId());
            orderDao.deleteOrder(orderId);
            int availableTickets = flight.getAvailableAdultTickets();
            flight.setAvailableAdultTickets(availableTickets + ticketsFreed);
            flightService.updateFlight(flight);
            return true;
        } else {
            return false;
        }

    }

}
