package kazmierczak.jan.persistence.dao;

import kazmierczak.jan.persistence.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketEntityDao extends JpaRepository<TicketEntity, Long> {
    List<TicketEntity> findAllByUser_Id(Long id);
}
