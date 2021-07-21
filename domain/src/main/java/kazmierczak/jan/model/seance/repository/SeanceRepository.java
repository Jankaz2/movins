package kazmierczak.jan.model.seance.repository;

import kazmierczak.jan.config.repository.CrudRepository;
import kazmierczak.jan.model.seance.Seance;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SeanceRepository extends CrudRepository<Seance, Long> {
    Optional<Seance> findByDate(LocalDate date);
    List<Seance> saveAll(List<Seance> seances);
    List<Seance> findAllByCinemaRoomId(Long id);
}
