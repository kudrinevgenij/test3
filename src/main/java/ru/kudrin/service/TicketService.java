package ru.kudrin.service;

import ru.kudrin.dao.TicketDao;
import ru.kudrin.dto.TicketDto;

import java.util.List;
import java.util.stream.Collectors;

public class TicketService {
    private static final TicketService INSTANCE = new TicketService();
    private static final TicketDao ticketDao = TicketDao.getINSTANCE();

    public static TicketService getInstance() {
        return INSTANCE;
    }

    private TicketService() {
    }

    public List<TicketDto> findAllByFlightId(Long id) {
        return ticketDao.findAllByFlightId(id).stream().map(
                ticket -> new TicketDto(ticket.getId(), ticket.getFlight().getId(), ticket.getSeatNo())
        ).collect(Collectors.toList());
    }
}
