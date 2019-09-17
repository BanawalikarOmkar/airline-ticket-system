package com.examples.flywithus.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.examples.flywithus.entity.Flight;
import com.examples.flywithus.service.IFlightService;

@Controller
@CrossOrigin
@RequestMapping("home")
public class FlightController {
    
    @Autowired
    private IFlightService flightService;
    
    
    @SuppressWarnings("boxing")
    @GetMapping("flight/{id}")
    public ResponseEntity<Flight> getflightById(@PathVariable("id") Integer id) {
        Flight flight = flightService.getFlightById(id);
        return new ResponseEntity<Flight>(flight, HttpStatus.OK);
    }
    
    @GetMapping("flight/{startDate}/{numberOfAdults}/{fromAirport}/{toAirport}")
    public ResponseEntity<List<Flight>> getMatchedFlights(
            @PathVariable("startDate")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, 
            @PathVariable("numberOfAdults") int numberOfAdults, 
            @PathVariable("fromAirport") int fromAirport, 
            @PathVariable("toAirport") int toAirport) {
        java.sql.Date temp = new java.sql.Date(startDate.getTime());
        List<Flight> list = flightService.getMatchedFlights(temp, numberOfAdults, fromAirport, toAirport);
        return new ResponseEntity<List<Flight>>(list, HttpStatus.OK);
    }
    
    @PostMapping("flight")
    public ResponseEntity<Void> addflight(@RequestBody Flight flight, UriComponentsBuilder builder) {
        boolean flag = flightService.addFlight(flight);
        if (flag == false) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/flight/{id}").buildAndExpand(flight.getFlightId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    @PutMapping("flight")
    public ResponseEntity<Flight> updateflight(@RequestBody Flight flight) {
        flightService.updateFlight(flight);
        return new ResponseEntity<Flight>(flight, HttpStatus.OK);
    }
    @DeleteMapping("flight/{id}")
    public ResponseEntity<Void> deleteflight(@PathVariable("id") Integer id) {
        flightService.deleteFlight(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }   

}
