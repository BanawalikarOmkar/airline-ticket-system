package com.examples.flywithus.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.examples.flywithus.TestUtils;
import com.examples.flywithus.config.JwtTokenUtil;
import com.examples.flywithus.dao.JwtRequest;
import com.examples.flywithus.entity.Flight;
import com.examples.flywithus.entity.UserInfo;
import com.examples.flywithus.service.FlightService;
import com.examples.flywithus.service.JWTUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(FlightController.class)
public class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    FlightService flightService;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @MockBean
    private JWTUserDetailsService jwtUserDetailsService;

    private final String URL = "/flight/";

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    public void getflightById_withoutRole() throws Exception {
        this.mockMvc.perform(get("/flight/109")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void getflightById_withUserRole() throws Exception {
        this.mockMvc.perform(get("/home/flight/109")).andExpect(status().is2xxSuccessful());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void getflightById_withAdminRole() throws Exception {
        this.mockMvc.perform(get("/home/flight/109")).andExpect(status().is2xxSuccessful());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void getMatchedFlights_test1() throws Exception {
        // Arrange
        Long longDate = 11223321111L;
        Date date = new Date(longDate);
        Integer flightID = 100;
        Flight flightstub = new Flight();
        flightstub.setFlightId(flightID);
        flightstub.setStartDate(date);
        flightstub.setAvailableAdultTickets(10);
        List<Flight> temp = new ArrayList<Flight>();
        temp.add(flightstub);
        when(flightService.getMatchedFlights(any(Date.class), any(Integer.class), any(Integer.class),
                any(Integer.class))).thenReturn(temp);

        // ACT and Assert
        MvcResult result = this.mockMvc.perform(get("/home/flight/2019-08-03/3/12/13"))
                .andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$").value(hasSize(temp.size())))
                .andExpect(jsonPath("$[0].startDate", is(longDate))).andReturn();

    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void addflight_test1() throws Exception {

        Long longDate = 11223321111L;
        Date date = new Date(longDate);
        Integer flightID = 100;
        Flight flightstub = new Flight();
        flightstub.setFlightId(flightID);
        flightstub.setStartDate(date);
        flightstub.setAvailableAdultTickets(10);

        when(flightService.addFlight(any(Flight.class))).thenReturn(true);

        MvcResult result = this.mockMvc
                .perform(
                        post("/home/flight/").contentType(MediaType.APPLICATION_JSON).content(asJsonString(flightstub)))
                .andExpect(status().is2xxSuccessful()).andReturn();

    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void updateFlight_test1() throws Exception {

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
        }).when(flightService).updateFlight(any(Flight.class));

//        Act and Assert
        MvcResult result = this.mockMvc
                .perform(put("/home/flight/").contentType(MediaType.APPLICATION_JSON).content(asJsonString(flightstub)))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void deleteFlight_test1() throws Exception {
        this.mockMvc.perform(delete("/home/flight/109")).andExpect(status().is2xxSuccessful());

    }

    // @Test
    public void testgetflightById() throws Exception {

        // Arrange
        Date date = new Date(11223321111l);
        Integer flightID = 100;
        Flight flightstub = new Flight();
        flightstub.setFlightId(flightID);
        flightstub.setStartDate(date);
        flightstub.setAvailableAdultTickets(10);
        when(flightService.getFlightById(flightID)).thenReturn(flightstub);

        // Authority authority = new Authority();
        // authority.setId(1L);
        // authority.setName(AuthorityName.ROLE_ADMIN);
        // List<Authority> authorities = Arrays.asList(authority);

        UserInfo user = new UserInfo();
        user.setUserName("obanawal");
        user.setPassword("temp");
        user.setRole("ROLE_ADMIN");
        JwtRequest jwtUser = new JwtRequest(user.getUserName(), user.getPassword());

        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
        UserDetails userDetails = (UserDetails) new User(user.getUserName(), user.getPassword(),
                Arrays.asList(authority));

        when(jwtTokenUtil.getUsernameFromToken(any())).thenReturn(user.getUserName());

        when(jwtUserDetailsService.loadUserByUsername(eq(user.getUserName()))).thenReturn(userDetails);

        // ACT
        MvcResult result = mockMvc
                .perform(get("/home/flight/" + flightID).accept(MediaType.APPLICATION_JSON_UTF8)
                        .header("Authorization", "Bearer nsodunsodiuv").content(TestUtils.objectToJson(flightstub)))
                .andReturn();

        // Assert
        int status = result.getResponse().getStatus();
        assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);

        // verify that service method was called once
        verify(flightService).getFlightById(100);

        // TestUtils.jsonToObject(result.getResponse()
        // .getContentAsString(), Flight.class);

    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
