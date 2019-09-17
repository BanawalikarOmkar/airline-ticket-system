package com.examples.flywithus.service;

import java.sql.Date;
import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.examples.flywithus.entity.Flight;


public interface IFlightService {
    @Secured ({"ROLE_ADMIN", "ROLE_USER"})
    List<Flight> getMatchedFlights(Date startTime, int numberOfAdults, int fromAirport, int toAirport);
    
    @Secured ({"ROLE_ADMIN", "ROLE_USER"})
    Flight getFlightById(int flightId);
    
    @Secured ({"ROLE_ADMIN"})
    boolean addFlight(Flight flight);
    
    @Secured ({"ROLE_ADMIN", "ROLE_USER"})
    void updateFlight(Flight flight);
    
    @Secured ({"ROLE_ADMIN"})
    void deleteFlight(int flightid);

    @Secured ({"ROLE_ADMIN", "ROLE_USER"})
    List<Flight> getAll();
}
