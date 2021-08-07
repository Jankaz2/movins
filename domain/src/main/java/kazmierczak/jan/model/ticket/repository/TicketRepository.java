package kazmierczak.jan.model.ticket.repository;

import kazmierczak.jan.config.repository.CrudRepository;
import kazmierczak.jan.model.ticket.Ticket;

import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
    List<Ticket> findTicketsByUserId(Long id);
}
