package kazmierczak.jan.model.ticket.repository;

import kazmierczak.jan.config.repository.CrudRepository;
import kazmierczak.jan.model.ticket.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
}
