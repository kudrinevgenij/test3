package ru.kudrin;

import ru.kudrin.dao.FlightDao;
import ru.kudrin.dao.TicketDao;
import ru.kudrin.dto.TicketFilter;
import ru.kudrin.entity.Ticket;
import ru.kudrin.utils.ConnectionManager;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JdbcRunner {
    public static void main(String[] args) throws SQLException {
        var ticketDao = TicketDao.getINSTANCE();
        System.out.println(ticketDao.findById(3L));

//        var flightDao = FlightDao.getInstance();
//        System.out.println(flightDao.findAll());







//        Ticket ticket = new Ticket();
//        ticket.setCost(BigDecimal.TEN);
//        ticket.setFlightId(1001L);
//        ticket.setPassengerName("Andrei");
//        ticket.setSeatNo("2B");
//        ticket.setPassportNo("abc123");
//        System.out.println(ticketDao.save(ticket));
    }

    public static List<Long> getTicketsByFlightId(Long id) {
        List<Long> tickets = new ArrayList<>();
        String sql = """
                select * from ticket where flight_id = ?
                """;

        try (var connection = ConnectionManager.get();
        var statement = connection.prepareStatement(sql)) {
            statement.setFetchSize(2);
            statement.setMaxRows(2);
            statement.setQueryTimeout(1);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                tickets.add(result.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    public static List<Long> getFlightBetween(LocalDateTime start, LocalDateTime end) {
        List<Long> tickets = new ArrayList<>();
        String sql = """
                SELECT * FROM flight
                WHERE departure_date BETWEEN ? AND ?;
                """;
        try (var connection = ConnectionManager.get(); var statement = connection.prepareStatement(sql)) {
            statement.setTimestamp(1, Timestamp.valueOf(start));
            statement.setTimestamp(2, Timestamp.valueOf(end));
            var result = statement.executeQuery();
            while (result.next()) {
                tickets.add(result.getLong("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tickets;
    }
}