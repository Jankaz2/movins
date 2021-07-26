package kazmierczak.jan.persistence.repository;

import kazmierczak.jan.model.ticket.Ticket;
import kazmierczak.jan.model.ticket.repository.TicketRepository;
import kazmierczak.jan.persistence.dao.TicketEntityDao;
import kazmierczak.jan.persistence.entity.TicketEntity;
import kazmierczak.jan.persistence.exception.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static kazmierczak.jan.persistence.entity.TicketEntity.*;

@Repository
@RequiredArgsConstructor
public class TicketRepositoyImpl implements TicketRepository {
    private final TicketEntityDao ticketEntityDao;

    /**
     * @param ticket we want to save
     * @return saved object
     */
    @Override
    public Optional<Ticket> add(Ticket ticket) {
        var ticketEntity = fromTicketToEntity(ticket);
        var insertedTicket = ticketEntityDao.save(ticketEntity);

        return Optional.ofNullable(insertedTicket.toTicket());
    }

    /**
     * @param id of ticket we want to delete
     * @return deleted object
     */
    @Override
    public Optional<Ticket> delete(Long id) {
        var ticketToDelete = ticketEntityDao
                .findById(id)
                .orElseThrow(() -> new PersistenceException("Cannot find ticket with this id: " + id));
        ticketEntityDao.delete(ticketToDelete);

        return Optional.ofNullable(ticketToDelete.toTicket());
    }

    /**
     * @param id of ticket we want to find by
     * @return found ticket
     */
    @Override
    public Optional<Ticket> findById(Long id) {
        return ticketEntityDao
                .findById(id)
                .map(TicketEntity::toTicket);
    }

    /**
     * @return list of all tickets
     */
    @Override
    public List<Ticket> findAll() {
        return ticketEntityDao
                .findAll()
                .stream()
                .map(TicketEntity::toTicket)
                .toList();
    }

    /**
     *
     * @param ids of tickets we want to find by
     * @return list of all tickets
     */
    @Override
    public List<Ticket> findAllById(List<Long> ids) {
        return ticketEntityDao
                .findAllById(ids)
                .stream()
                .map(TicketEntity::toTicket)
                .toList();
    }
}
