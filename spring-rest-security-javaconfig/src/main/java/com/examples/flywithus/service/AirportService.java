package com.examples.flywithus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examples.flywithus.dao.IAirportDAO;
import com.examples.flywithus.entity.Airport;

@Service
public class AirportService implements IAirportService {
    
    @Autowired
    private IAirportDAO airportDAO; 
    
    /* (non-Javadoc)
     * @see com.examples.flywithus.service.IAirportService#getAllAiports()
     */
    @Override
    public List<Airport> getAllAiports(){
        return airportDAO.getAllAirports();
    }

    /* (non-Javadoc)
     * @see com.examples.flywithus.service.IAirportService#getAiport(int)
     */
    @Override
    public Airport getAiport(int airportId){
        return airportDAO.getAirport(airportId);
        
    }
}
