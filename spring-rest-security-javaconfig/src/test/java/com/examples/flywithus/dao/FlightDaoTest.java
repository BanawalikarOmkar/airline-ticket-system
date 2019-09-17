package com.examples.flywithus.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.examples.flywithus.entity.Flight;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class FlightDaoTest {

    @Autowired
    private FlightDAO flightDAO;

    long longDate = 11223321111L;

    @Test
    public void addFlight_test1() {

        // Arrange
        Flight flightstub = getFlight(100);

        flightstub.setAvailableAdultTickets(10);

        // Act
        flightDAO.addFlight(flightstub);

        // Assert
        Flight result = flightDAO.getFlight(flightstub.getFlightId());
        assertEquals(result.getFlightId(), 100);
        assertEquals(result.getFromLocation(), 12);
        assertEquals(result.getToLocation(), 13);
    }

    @Test
    public void getMatchedFlights_test1() {

        // Arrange
        Flight flightstub = getFlight(101);
        flightstub.setAvailableAdultTickets(10);

        // Act
        flightDAO.addFlight(flightstub);
        flightDAO.addFlight(getFlight(102));
        // Assert
        List<Flight> result = flightDAO.getMatchedFlights(new Date(longDate), 3, 12, 13);
        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getFromLocation(), 12);
        assertEquals(result.get(1).getFromLocation(), 12);

    }

    @Test
    public void updateFlight_test1() {

        // Arrange
        Flight flightstub = getFlight(101);
        flightstub.setAvailableAdultTickets(10);
        flightDAO.addFlight(flightstub);
        

        // Act
        Flight flight = flightDAO.getFlight(101);
        flight.setAvailableAdultTickets(3);
        flightDAO.updateFlight(flight);

        // Assert
        Flight resultflight = flightDAO.getFlight(101);
        assertEquals(resultflight.getAvailableAdultTickets(), 3);

    }

    @Test
    public void deleteFlight_test1() {
        // Arrange
        Flight flightstub = getFlight(101);
        flightstub.setAvailableAdultTickets(10);

        // Act
        flightDAO.addFlight(flightstub);
        flightDAO.deleteFlight(101);

        // Assert
        Flight resultflight = flightDAO.getFlight(101);
        assertEquals(resultflight, null);
    }

    @Test
    public void getAll_test1() {
        
        // Arrange
        flightDAO.addFlight(getFlight(101));
        flightDAO.addFlight(getFlight(102));

        // Act
        List<Flight> result = flightDAO.getAll();
        
        // Assert
        assertEquals(result.size(), 2);
    }

    private Flight getFlight(int flightid) {

        Date date = new Date(longDate);
        Flight flightstub = new Flight();
        flightstub.setFlightId(flightid);
        flightstub.setStartDate(date);
        flightstub.setStartTime(Time.valueOf("17:05:00"));
        flightstub.setBaggageInfo("23kg");
        flightstub.setEndDate(date);
        flightstub.setEndTime(Time.valueOf("18:05:00"));
        flightstub.setFlightNumber("209");
        flightstub.setFromAirport("bangalore");
        flightstub.setFromLocation(12);
        flightstub.setRefundableType(0);
        flightstub.setToAirport("Hyderabad");
        flightstub.setToLocation(13);
        flightstub.setTotalPrice(1200);
        flightstub.setAvailableAdultTickets(10);
        return flightstub;
    }
}
