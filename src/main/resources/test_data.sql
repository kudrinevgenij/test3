--insert into flight (id, aircraft_id, arrival_airport_code, arrival_date, departure_airport_code, departure_date, flight_no, status)
--values
--(1, 1, 001, 01-01-2025, 010, 01-01-2025, '1', 'a'),
--(2, 2, 002, 01-01-2025, 011, 01-01-2025, '1', 'a'),
--(3, 1, 003, 01-01-2025, 012, 01-01-2025, '1', 'a'),
--(4, 2, 004, 01-01-2025, 015, 01-01-2025, '1', 'a'),
--(5, 1, 005, 01-01-2025, 020, 01-01-2025, '1', 'a');
--
--insert into ticket (id, passport_no, passenger_name, flight_id, seat_no,  coast)
--values
--(1, 1122335367, 'Иван Иванов', 1, 'A1', 200.00),
--(2, 321424377, 'Петр Петров', 2, 'B1', 180.00),
--(3, 112221333, 'Светлана Светикова', 3, 'B2', 175.00),
--(4, 1122143233, 'Андрей Андреев', 1, 'C1', 200.00),
--(5, 11287957233, 'Иван Кожемякин', 1, 'A2', 250.00),
--(6, 1167532233, 'Олег Рубцов', 4, 'C3', 120.00),
--(7, 112456233, 'Екатерина Петренко', 1, 'C4', 140.00),
--(8, 112290733, 'Иван Розмаринов', 3, 'B2', 160.00),
--(9, 1122456733, 'Максим Комсомольцев', 2, 'A1', 180.00),
--(10, 112357233, 'Иван Александров', 3, 'A1', 175.00);
--
--
-- ======================
-- AIRPORT
-- ======================
insert into airport (code, country, city) values
('SVO', 'Russia', 'Moscow'),
('LED', 'Russia', 'Saint Petersburg'),
('KZN', 'Russia', 'Kazan'),
('IST', 'Turkey', 'Istanbul'),
('DXB', 'UAE', 'Dubai');

-- ======================
-- AIRCRAFT
-- ======================
insert into aircraft (id, model, image) values
(1, 'Airbus A320', null),
(2, 'Boeing 737-800', null),
(3, 'Sukhoi Superjet 100', null);

-- ======================
-- SEAT
-- ======================
-- A320 (24 seats)
insert into seat (aircraft_id, seat_no)
select 1, row || col
from generate_series(1, 6) row,
     unnest(array['A','B','C','D']) col;

-- Boeing 737 (36 seats)
insert into seat (aircraft_id, seat_no)
select 2, row || col
from generate_series(1, 6) row,
     unnest(array['A','B','C','D','E','F']) col;

-- Superjet (12 seats)
insert into seat (aircraft_id, seat_no)
select 3, row || col
from generate_series(1, 4) row,
     unnest(array['A','B','C']) col;

-- ======================
-- FLIGHT
-- ======================
insert into flight (
    id,
    flight_no,
    departure_date,
    departure_airport_code,
    arrival_date,
    arrival_airport_code,
    aircraft_id,
    status
) values
(1001, 'SU100', now() + interval '1 day', 'SVO', now() + interval '1 day 1 hour', 'LED', 1, 'SCHEDULED'),
(1002, 'SU200', now() + interval '2 day', 'LED', now() + interval '2 day 2 hour', 'KZN', 2, 'DELAYED'),
(1003, 'TK300', now() + interval '3 day', 'SVO', now() + interval '3 day 5 hour', 'IST', 2, 'SCHEDULED'),
(1004, 'EK400', now() + interval '4 day', 'IST', now() + interval '4 day 6 hour', 'DXB', 3, 'CANCELLED');

-- ======================
-- TICKET
-- ======================
insert into ticket (
    id,
    passport_no,
    passenger_name,
    flight_id,
    seat_no,
    coast
) values
(1, 'AA123456', 'Ivan Ivanov', 1001, '1A', 5500.00),
(2, 'BB234567', 'Petr Petrov', 1001, '1B', 5500.00),
(3, 'CC345678', 'Anna Smirnova', 1002, '2C', 7200.00),
(4, 'DD456789', 'John Smith', 1003, '3D', 18500.00),
(5, 'EE567890', 'Maria Garcia', 1004, '1A', 21000.00);
