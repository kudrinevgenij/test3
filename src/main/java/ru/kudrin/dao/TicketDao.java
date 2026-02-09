package ru.kudrin.dao;

import ru.kudrin.dto.TicketFilter;
import ru.kudrin.entity.Ticket;
import ru.kudrin.exception.DaoException;
import ru.kudrin.utils.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TicketDao implements Dao<Long, Ticket> {
    private final static TicketDao INSTANCE = new TicketDao();
    private final static FlightDao flightDao = FlightDao.getInstance();
    private final static String SAVE_SQL = """
                INSERT INTO ticket (id, coast, flight_id, passenger_name, passport_no, seat_no)
                VALUES (nextval('ticket_sequence'), ?, ?, ?, ?, ?);
                """;

    private final static String DELETE_SQL = """
                DELETE FROM ticket
                WHERE id = ?;
                """;
    private final static String FIND_ALL_SQL = """
            SELECT t.id, t.coast, t.flight_id, t.passenger_name, t.passport_no, t.seat_no,
            f.aircraft_id, f.arrival_airport_code, f.arrival_date, 
            f.departure_airport_code, f.departure_date, f.flight_no, f.status
            FROM ticket t
            JOIN flight f on f.id = t.flight_id
            """;

    private final static String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE t.id = ?;
            """;
    private final static String UPDATE_SQL = """
            UPDATE ticket
            SET passport_no = ?,
                passenger_name = ?,
                flight_id = ?,
                seat_no = ?,
                coast = ?
            WHERE id = ?;
            """;
    private final static String FIND_BY_FLIGHT_ID_SQL = FIND_ALL_SQL + """
            WHERE t.flight_id = ?;
            """;

    public boolean update(Ticket ticket) {
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(UPDATE_SQL)) {
                    statement.setString(1, ticket.getPassportNo());
                    statement.setString(2, ticket.getPassengerName());
                    statement.setLong(3, ticket.getFlight().getId());
                    statement.setString(4, ticket.getSeatNo());
                    statement.setBigDecimal(5, ticket.getCost());
                    statement.setLong(6, ticket.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Ticket save(Ticket ticket) {

        try(var connection = ConnectionManager.get();
        var statement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setBigDecimal(1, ticket.getCost());
            statement.setLong(2, ticket.getFlight().getId());
            statement.setString(3, ticket.getPassengerName());
            statement.setString(4, ticket.getPassportNo());
            statement.setString(5, ticket.getSeatNo());
            statement.executeUpdate();

            var keys = statement.getGeneratedKeys();
            if(keys.next()) {
                ticket.setId(keys.getLong("id"));
            }
            return ticket;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public boolean delete(Long id) {
        try(var connection = ConnectionManager.get();
        var statement = connection.prepareStatement(DELETE_SQL)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<Ticket> findAllByFlightId(Long id) {
        try(var connection = ConnectionManager.get();
        var statment = connection.prepareStatement(FIND_BY_FLIGHT_ID_SQL)) {
            List<Ticket> result = new ArrayList<>();
            statment.setLong(1, id);
            var resultSet = statment.executeQuery();
            while (resultSet.next()) {
                result.add(buildTicket(resultSet));
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<Ticket> findAll() {
        try (var connection = ConnectionManager.get();
        var statement = connection.prepareStatement(FIND_ALL_SQL)) {
            List<Ticket> tickets = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next()) {
                Ticket ticket = buildTicket(result);
                tickets.add(ticket);
            }
            return tickets;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<Ticket> findAll(TicketFilter filter) {
        List<Object> parameters = new ArrayList<>();
        List<String> whereSQL = new ArrayList<>();
        if(filter.passengerName() != null) {
            parameters.add(filter.passengerName());
            whereSQL.add("passenger_name = ?");
        }

        if(filter.seatNo() != null) {
            parameters.add("%" + filter.seatNo() + "%");
            whereSQL.add("seat_no LIKE ?");
        }
        parameters.add(filter.limit());
        parameters.add(filter.offset());
        String sql = FIND_ALL_SQL + whereSQL.stream().collect(Collectors.joining(
                " AND ",
                parameters.size() > 2 ? " WHERE " : " ",
                " LIMIT ? OFFSET ? "
        ));
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(sql)) {

            List<Ticket> tickets = new ArrayList<>();
            for (int i = 0; i < parameters.size(); i++) {
                statement.setObject(i + 1, parameters.get(i));
            }
            System.out.println(sql);

            var result = statement.executeQuery();
            while (result.next()) {
                Ticket ticket = buildTicket(result);
                tickets.add(ticket);
            }
            return tickets;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Optional<Ticket> findById (Long id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            Ticket ticket = null;
            statement.setLong(1, id);
            var result = statement.executeQuery();
            if (result.next()) {
                ticket = buildTicket(result);
            }
            return Optional.ofNullable(ticket);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    private static Ticket buildTicket(ResultSet result) throws SQLException {
//        var flight = new Flight(
//                result.getLong("flight_id"),
//                result.getInt("aircraft_id"),
//                result.getString("arrival_airport_code"),
//                result.getTimestamp("arrival_date").toLocalDateTime(),
//                result.getString("departure_airport_code"),
//                result.getTimestamp("departure_date").toLocalDateTime(),
//                result.getString("flight_no"),
//                FlightStatus.valueOf(result.getString("status"))
//        );
        return new Ticket(
                result.getLong("id"),
                result.getBigDecimal("coast"),
                flightDao.findById(
                        result.getLong("flight_id"),
                        result.getStatement().getConnection()
                ).orElse(null),
                result.getString("passenger_name"),
                result.getString("passport_no"),
                result.getString("seat_no")
        );
    }

    public static TicketDao getINSTANCE() {
        return INSTANCE;
    }

    private TicketDao() {

    }
}
