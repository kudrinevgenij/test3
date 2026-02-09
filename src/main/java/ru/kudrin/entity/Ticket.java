package ru.kudrin.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Ticket {
    private Long id;
    private BigDecimal cost;
    private Flight flight;
    private String passengerName;
    private String passportNo;
    private String seatNo;

    public Ticket() {
    }

    public Ticket(Long id, BigDecimal cost, Flight flightId, String passengerName, String passportNo, String seatNo) {
        this.id = id;
        this.cost = cost;
        this.flight = flightId;
        this.passengerName = passengerName;
        this.passportNo = passportNo;
        this.seatNo = seatNo;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public Flight getFlight() {
        return flight;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", cost=" + cost +
                ", flightId=" + flight +
                ", passengerName='" + passengerName + '\'' +
                ", passportNo='" + passportNo + '\'' +
                ", seatNo='" + seatNo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id) && Objects.equals(cost, ticket.cost) && Objects.equals(flight, ticket.flight) && Objects.equals(passengerName, ticket.passengerName) && Objects.equals(passportNo, ticket.passportNo) && Objects.equals(seatNo, ticket.seatNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cost, flight, passengerName, passportNo, seatNo);
    }
}