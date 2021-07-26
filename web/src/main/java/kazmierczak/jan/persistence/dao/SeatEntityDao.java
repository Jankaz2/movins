package kazmierczak.jan.persistence.dao;

import kazmierczak.jan.persistence.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatEntityDao extends JpaRepository<SeatEntity, Long> {
}
