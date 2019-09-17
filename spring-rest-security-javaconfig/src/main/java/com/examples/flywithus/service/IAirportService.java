package com.examples.flywithus.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.examples.flywithus.entity.Airport;

public interface IAirportService {

    @Secured ({"ROLE_ADMIN", "ROLE_USER"})
    List<Airport> getAllAiports();

    @Secured ({"ROLE_ADMIN", "ROLE_USER"})
    Airport getAiport(int airportId);

}