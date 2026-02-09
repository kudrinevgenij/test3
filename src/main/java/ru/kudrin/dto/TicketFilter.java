package ru.kudrin.dto;

public record TicketFilter (String passengerName,
                            String seatNo,
                            int limit,
                            int offset) {

}
