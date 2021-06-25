package kazmierczak.jan.persistence.dao;

import kazmierczak.jan.persistence.entity.CinemaRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaRoomEntityDao extends JpaRepository<CinemaRoomEntity, Long> {
}
