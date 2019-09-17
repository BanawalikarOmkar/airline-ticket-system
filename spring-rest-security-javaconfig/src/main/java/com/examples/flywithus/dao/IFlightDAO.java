package com.examples.flywithus.dao;

import java.sql.Date;
import java.util.List;

import com.examples.flywithus.entity.Airport;
import com.examples.flywithus.entity.Flight;

public interface IFlightDAO {
    
    List<Flight> getMatchedFlights(Date startTime, int numberOfAdults, int fromAirport, int toAirport );

    Flight getFlight(int flightId);
    
    void updateFlight(Flight flight);
    
    void deleteFlight(int flightId );

    List<Flight> getAll();

    void addFlight(Flight flight);
}
