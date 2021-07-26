package kazmierczak.jan.persistence.dao;

import kazmierczak.jan.persistence.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketEntityDao extends JpaRepository<TicketEntity, Long> {
}
