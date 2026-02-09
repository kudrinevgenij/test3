package ru.kudrin.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Flight {
    private Long id;
    private Integer aircraftId;
    private String arrivalAirportCode;
    private LocalDateTime arrivalDate;
    private String departureAirportCode;
    private LocalDateTime departureDate;
    private String flightNo;
    private FlightStatus status;

    public Flight() {
    }

    public Flight(Long id, Integer aircraftId, String arrivalAirportCode, LocalDateTime arrivalDate, String departureAirportCode, LocalDateTime departureDate, String flightNo, FlightStatus status) {
        this.id = id;
        this.aircraftId = aircraftId;
        this.arrivalAirportCode = arrivalAirportCode;
        this.arrivalDate = arrivalDate;
        this.departureAirportCode = departureAirportCode;
        this.departureDate = departureDate;
        this.flightNo = flightNo;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Integer getAircraftId() {
        return aircraftId;
    }

    public String getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public FlightStatus getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAircraftId(Integer aircraftId) {
        this.aircraftId = aircraftId;
    }

    public void setArrivalAirportCode(String arrivalAirportCode) {
        this.arrivalAirportCode = arrivalAirportCode;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public void setStatus(FlightStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(id, flight.id) && Objects.equals(aircraftId, flight.aircraftId) && Objects.equals(arrivalAirportCode, flight.arrivalAirportCode) && Objects.equals(arrivalDate, flight.arrivalDate) && Objects.equals(departureAirportCode, flight.departureAirportCode) && Objects.equals(departureDate, flight.departureDate) && Objects.equals(flightNo, flight.flightNo) && status == flight.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, aircraftId, arrivalAirportCode, arrivalDate, departureAirportCode, departureDate, flightNo, status);
    }

    @Override
    public String toString() {
        return "Flight{" +
               "id=" + id +
               ", aircraftId=" + aircraftId +
               ", arrivalAirportCode='" + arrivalAirportCode + '\'' +
               ", arrivalDate=" + arrivalDate +
               ", departureAirportCode='" + departureAirportCode + '\'' +
               ", departureDate=" + departureDate +
               ", flightNo='" + flightNo + '\'' +
               ", status=" + status +
               '}';
    }
}
