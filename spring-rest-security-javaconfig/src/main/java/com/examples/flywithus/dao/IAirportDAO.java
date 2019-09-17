package com.examples.flywithus.dao;

import java.util.List;

import com.examples.flywithus.entity.Airport;
import com.examples.flywithus.entity.UserInfo;

public interface IAirportDAO {
    
    List<Airport> getAllAirports();
    
    Airport getAirport(int airportId);

}
