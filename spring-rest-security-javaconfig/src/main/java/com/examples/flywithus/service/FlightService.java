package com.examples.flywithus.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.examples.flywithus.dao.IFlightDAO;
import com.examples.flywithus.entity.Flight;

@Service
public class FlightService implements IFlightService {
    @Autowired
    private IFlightDAO flightDAO;

    @Override
    public List<Flight> getMatchedFlights(Date startTime, int numberOfAdults, int fromAirport, int toAirport) {
        return flightDAO.getMatchedFlights(startTime, numberOfAdults, fromAirport, toAirport);
    }
    
    @Override
    public List<Flight> getAll() {
        return flightDAO.getAll();
    }

    @Override
    public Flight getFlightById(int flightId) {
        return flightDAO.getFlight(flightId);
    }

    @Override
    public boolean addFlight(Flight flight) {
        try {
            flightDAO.addFlight(flight);
            return true;
        }catch(DataAccessException ex) {
            return false;
        }
        
        
    }

    @Override
    public void updateFlight(Flight flight) {
        flightDAO.updateFlight(flight);
    }

    @Override
    public void deleteFlight(int flightid) {
        flightDAO.deleteFlight(flightid);
    }

}
