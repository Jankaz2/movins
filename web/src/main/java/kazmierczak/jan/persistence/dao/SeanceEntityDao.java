package kazmierczak.jan.persistence.dao;

import kazmierczak.jan.persistence.entity.SeanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface SeanceEntityDao extends JpaRepository<SeanceEntity, Long> {
    Optional<SeanceEntity> findByDate(LocalDate date);
}
