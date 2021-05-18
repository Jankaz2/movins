package kazmierczak.jan.persistence.dao;

import kazmierczak.jan.persistence.entity.CinemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CinemaEntityDao extends JpaRepository<CinemaEntity, Long> {
    Optional<CinemaEntity> findByName(String name);
}
