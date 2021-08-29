package kazmierczak.jan.persistence.repository;

import kazmierczak.jan.model.seance.Seance;
import kazmierczak.jan.model.seance.repository.SeanceRepository;
import kazmierczak.jan.persistence.dao.SeanceEntityDao;
import kazmierczak.jan.persistence.entity.SeanceEntity;
import kazmierczak.jan.persistence.exception.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static kazmierczak.jan.persistence.entity.SeanceEntity.fromSeanceToEntity;
import static kazmierczak.jan.persistence.entity.SeanceEntity.fromSeancesToEntityList;

@Repository
@RequiredArgsConstructor
public class SeanceRepositoryImpl implements SeanceRepository {
    private final SeanceEntityDao seanceEntityDao;

    /**
     * @param seance we want to add
     * @return optional of nullable of added seance
     */
    @Override
    public Optional<Seance> add(Seance seance) {
        var insertedSeance = fromSeanceToEntity(seance);
        seanceEntityDao.save(insertedSeance);

        return Optional.ofNullable(insertedSeance.toSeance());
    }

    /**
     * @param id of seance we want to delete
     * @return optional of nullable of deleted seance
     */
    @Override
    public Optional<Seance> delete(Long id) {
        var seanceToDelete = seanceEntityDao.findById(id)
                .orElseThrow(() -> new PersistenceException("Cannot find seance with this id: " + id));
        seanceEntityDao.delete(seanceToDelete);

        return Optional.ofNullable(seanceToDelete.toSeance());
    }

    /**
     * @param id of seance we want to find
     * @return seance we found
     */
    @Override
    public Optional<Seance> findById(Long id) {
        return seanceEntityDao
                .findById(id)
                .map(SeanceEntity::toSeance);
    }

    /**
     * @return list of all seances
     */
    @Override
    public List<Seance> findAll() {
        return seanceEntityDao
                .findAll()
                .stream()
                .map(SeanceEntity::toSeance)
                .toList();
    }

    /**
     * @param ids of seances we want to find
     * @return list of found seances
     */
    @Override
    public List<Seance> findAllById(List<Long> ids) {
        return seanceEntityDao
                .findAllById(ids)
                .stream()
                .map(SeanceEntity::toSeance)
                .toList();
    }

    /**
     * @param date we want to find seance by
     * @return found seance
     */
    @Override
    public Optional<Seance> findByDate(LocalDate date) {
        return seanceEntityDao
                .findByDate(date)
                .map(SeanceEntity::toSeance);
    }

    /**
     * @param seances we want to save
     * @return saved seances
     */
    @Override
    public List<Seance> saveAll(List<Seance> seances) {
        var seanceEntities = fromSeancesToEntityList(seances);

        var insertedSeances = seanceEntityDao
                .saveAll(seanceEntities);

        return insertedSeances
                .stream()
                .map(SeanceEntity::toSeance)
                .toList();
    }

    /**
     * @param id of cinema we want to find seances by
     * @return list of seances
     */
    @Override
    public List<Seance> findAllByCinemaRoomId(Long id) {
        return seanceEntityDao
                .findAllByCinemaRoom_Id(id)
                .stream()
                .map(SeanceEntity::toSeance)
                .toList();
    }
}
