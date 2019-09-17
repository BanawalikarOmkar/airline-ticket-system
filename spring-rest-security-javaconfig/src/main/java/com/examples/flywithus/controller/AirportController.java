package com.examples.flywithus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.examples.flywithus.entity.Airport;
import com.examples.flywithus.service.IAirportService;

@Controller
@CrossOrigin
@RequestMapping("home")
public class AirportController {

    @Autowired
    private IAirportService airportService;

    @GetMapping("airport/{id}")
    public ResponseEntity<Airport> getAirportById(@PathVariable("id") Integer id) {
        Airport airport = airportService.getAiport(id);
        return new ResponseEntity<Airport>(airport, HttpStatus.OK);
    }

    @GetMapping("airport")
    public ResponseEntity<List<Airport>> getAllAirports() {
        List<Airport> airports = airportService.getAllAiports();
        return new ResponseEntity<List<Airport>>(airports, HttpStatus.OK);
    }

}
