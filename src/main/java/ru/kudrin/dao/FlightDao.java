package ru.kudrin.dao;

import ru.kudrin.entity.Flight;
import ru.kudrin.entity.FlightStatus;
import ru.kudrin.exception.DaoException;
import ru.kudrin.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightDao implements Dao<Long, Flight> {
    private static final FlightDao INSTANCE = new FlightDao();
    private static final String FIND_ALL_SQL = """
            SELECT id, aircraft_id, arrival_airport_code, arrival_date, 
            departure_airport_code, departure_date, flight_no, status 
            FROM flight""";

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
             WHERE id = ?
            """;
    @Override
    public Flight save(Flight flight) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean update(Flight flight) {
        return false;
    }

    @Override
    public Optional<Flight> findById(Long id) {
        try (var connection = ConnectionManager.get()) {
            return findById(id, connection);
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    public Optional<Flight> findById(Long id, Connection connection) {

        try (var statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            Flight flight = null;
            statement.setLong(1, id);
            var result = statement.executeQuery();
            if (result.next()) {
                flight = buildFlight(result);
            }
            return Optional.ofNullable(flight);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Flight> findAll() {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_ALL_SQL)) {
            List<Flight> flights = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next()) {
                Flight ticket = buildFlight(result);
                flights.add(ticket);
            }
            return flights;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Flight buildFlight(ResultSet result) throws SQLException {
        return new Flight(
                result.getLong("id"),
                result.getInt("aircraft_id"),
                result.getString("arrival_airport_code"),
                result.getTimestamp("arrival_date").toLocalDateTime(),
                result.getString("departure_airport_code"),
                result.getTimestamp("departure_date").toLocalDateTime(),
                result.getString("flight_no"),
                FlightStatus.valueOf(result.getString("status"))
        );
    }


    public static FlightDao getInstance() {
        return INSTANCE;
    }

    private FlightDao() {

    }
}
