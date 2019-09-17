package com.examples.flywithus.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "flights")
public class Flight implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "flight_id")
    private int flightId;
    
    @Column(name = "fromLocation")
    private int fromLocation;
    
    @Column(name = "toLocation")
    private int toLocation;
    
    @Column(name = "fromAirport")
    private String fromAirport;
    
    @Column(name = "toAirport")
    private String toAirport;
    
    @Column(name = "flightNumber")
    private String flightNumber;

    @Column(name = "totalPrice")
    private int totalPrice;

    @Column(name = "baggageinfo")
    private String baggageInfo;

    @Column(name = "refundableType")
    private int refundableType;

    @Column(name = "startDate")
    private Date startDate;

    @Column(name = "startTime")
    private Time startTime;

    @Column(name = "endDate")
    private Date endDate;

    @Column(name = "endTime")
    private Time endTime;

    @Column(name = "availableAdultTickets")
    private int availableAdultTickets;

    
    
    public String getFromAirport() {
        return fromAirport;
    }

    public void setFromAirport(String fromAirport) {
        this.fromAirport = fromAirport;
    }

    public String getToAirport() {
        return toAirport;
    }

    public void setToAirport(String toAirport) {
        this.toAirport = toAirport;
    }

    public int getAvailableAdultTickets() {
        return availableAdultTickets;
    }

    public void setAvailableAdultTickets(int availableAdultTickets) {
        this.availableAdultTickets = availableAdultTickets;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public int getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(int fromLocation) {
        this.fromLocation = fromLocation;
    }

    public int getToLocation() {
        return toLocation;
    }

    public void setToLocation(int toLocation) {
        this.toLocation = toLocation;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getBaggageInfo() {
        return baggageInfo;
    }

    public void setBaggageInfo(String baggageInfo) {
        this.baggageInfo = baggageInfo;
    }

    public int getRefundableType() {
        return refundableType;
    }

    public void setRefundableType(int refundableType) {
        this.refundableType = refundableType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

}
