package com.examples.flywithus.entity;

import java.net.URL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="airports")
public class Airport {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="airport_id")
    private int airportId;
    @Column(name="name")
    private String name;
    @Column(name="flag")
    private URL flag;
    @Column(name="country")
    private String country;
    
    public int getAirportId() {
        return airportId;
    }
    public void setAirportId(int airportId) {
        this.airportId = airportId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public URL getFlag() {
        return flag;
    }
    public void setFlag(URL flag) {
        this.flag = flag;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    

}
