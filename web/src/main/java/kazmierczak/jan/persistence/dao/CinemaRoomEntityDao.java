package kazmierczak.jan.persistence.dao;

import kazmierczak.jan.persistence.entity.CinemaRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CinemaRoomEntityDao extends JpaRepository<CinemaRoomEntity, Long> {
    List<CinemaRoomEntity> findCinemaRoomEntitiesByCinema_Name(String name);
}
