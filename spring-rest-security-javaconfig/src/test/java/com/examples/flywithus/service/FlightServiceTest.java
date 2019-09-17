package com.examples.flywithus.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import com.examples.flywithus.dao.IFlightDAO;
import com.examples.flywithus.entity.Flight;

@RunWith(SpringRunner.class)
@SpringBootTest

public class FlightServiceTest {

    @Autowired
    private IFlightService flightService;

    @MockBean
    private IFlightDAO flightDAO;

    @TestConfiguration
    static class FlightServiceImplTestContextConfiguration {

        @Bean
        public IFlightService employeeService() {
            return new FlightService();
        }
    }

    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void getMatchedFlights_test2() {
        Long longDate = 11223321111L;
        Date date = new Date(longDate);
        List<Flight> resultFlight = flightService.getMatchedFlights(date, 12, 12, 13);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void getMatchedFlights_test1() {

        Long longDate = 11223321111L;
        Date date = new Date(longDate);
        Integer flightID = 100;
        Flight flightstub = new Flight();
        flightstub.setFlightId(flightID);
        flightstub.setStartDate(date);
        flightstub.setAvailableAdultTickets(10);
        List<Flight> temp = new ArrayList<Flight>();
        temp.add(flightstub);
        when(flightDAO.getMatchedFlights(any(Date.class), any(Integer.class), any(Integer.class), any(Integer.class)))
                .thenReturn(temp);

        List<Flight> resultFlight = flightService.getMatchedFlights(date, 12, 12, 13);

        assertEquals(resultFlight.get(0).getFlightId(), flightstub.getFlightId());

    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void addFlight_test1() {

        Long longDate = 11223321111L;
        Date date = new Date(longDate);
        Integer flightID = 100;
        Flight flightstub = new Flight();
        flightstub.setFlightId(flightID);
        flightstub.setStartDate(date);
        flightstub.setAvailableAdultTickets(10);
        doAnswer((Answer) invocation -> {
            Flight flight = (Flight) invocation.getArgument(0);

            // Assert
            assertEquals(flight.getFlightId(), flightstub.getFlightId());
            return null;
        }).when(flightDAO).addFlight(any(Flight.class));

        boolean result = flightService.addFlight(flightstub);

        assertEquals(result, true);
    }

    @WithMockUser(roles = "User")
    @Test(expected = AccessDeniedException.class)
    public void addFlight_test2() {

        // Arrange
        Long longDate = 11223321111L;
        Date date = new Date(longDate);
        Integer flightID = 100;
        Flight flightstub = new Flight();
        flightstub.setFlightId(flightID);
        flightstub.setStartDate(date);
        flightstub.setAvailableAdultTickets(10);
        doAnswer((Answer) invocation -> {
            Flight flight = (Flight) invocation.getArgument(0);

            // Assert
            assertEquals(flight.getFlightId(), flightstub.getFlightId());
            return null;
        }).when(flightDAO).addFlight(any(Flight.class));

        boolean result = flightService.addFlight(flightstub);

        assertEquals(result, true);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void deleteFlight_test1() {
        int deleteFlightnumber = 101;
        doAnswer((Answer) invocation -> {
            int flightid = (int) invocation.getArgument(0);

            // Assert
            assertEquals(flightid, deleteFlightnumber);
            return null;
        }).when(flightDAO).deleteFlight(any(Integer.class));

        flightService.deleteFlight(deleteFlightnumber);
    }

    @WithMockUser(roles = "User")
    @Test(expected = AccessDeniedException.class)
    public void deleteFlight_test2() {
        int deleteFlightnumber = 101;
        flightService.deleteFlight(deleteFlightnumber);
    }
    
    
    @Test
    @WithMockUser(roles = "ADMIN")
    public void updateFlight_test1() {

        // Arrange
        Long longDate = 11223321111L;
        Date date = new Date(longDate);
        Integer flightID = 100;
        Flight flightstub = new Flight();
        flightstub.setFlightId(flightID);
        flightstub.setStartDate(date);
        flightstub.setAvailableAdultTickets(10);
        doAnswer((Answer) invocation -> {
            Flight flight = (Flight) invocation.getArgument(0);

            // Assert
            assertEquals(flight.getFlightId(), flightstub.getFlightId());
            return null;
        }).when(flightDAO).updateFlight(any(Flight.class));

        // Act
        flightService.addFlight(flightstub);

    }

}
