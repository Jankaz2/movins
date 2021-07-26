package kazmierczak.jan.persistence.repository;

import kazmierczak.jan.model.seat.Seat;
import kazmierczak.jan.model.seat.repository.SeatRepository;
import kazmierczak.jan.persistence.dao.SeatEntityDao;
import kazmierczak.jan.persistence.entity.SeatEntity;
import kazmierczak.jan.persistence.exception.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static kazmierczak.jan.persistence.entity.SeatEntity.*;

@Repository
@RequiredArgsConstructor
public class SeatRepositoryImpl implements SeatRepository {
    private final SeatEntityDao seatEntityDao;

    /**
     *
     * @param seat we want to save
     * @return saved object
     */
    @Override
    public Optional<Seat> add(Seat seat) {
        var seatEntity = fromSeatToEntity(seat);
        var insertedSeat = seatEntityDao.save(seatEntity);

        return Optional.ofNullable(insertedSeat.toSeat());
    }

    /**
     *
     * @param id of seat we want to delete
     * @return deleted object
     */
    @Override
    public Optional<Seat> delete(Long id) {
        var seatToDelete = seatEntityDao
                .findById(id)
                .orElseThrow(() -> new PersistenceException("Cannot find seat with this id: " + id));
        seatEntityDao.delete(seatToDelete);

        return Optional.ofNullable(seatToDelete.toSeat());
    }

    /**
     *
     * @param id we want to find seat by
     * @return seat object
     */
    @Override
    public Optional<Seat> findById(Long id) {
        return seatEntityDao
                .findById(id)
                .map(SeatEntity::toSeat);
    }

    /**
     *
     * @return list of all seats
     */
    @Override
    public List<Seat> findAll() {
        return seatEntityDao
                .findAll()
                .stream()
                .map(SeatEntity::toSeat)
                .toList();
    }

    /**
     *
     * @param ids list we want to find by
     * @return list of seats
     */
    @Override
    public List<Seat> findAllById(List<Long> ids) {
        return seatEntityDao
                .findAllById(ids)
                .stream()
                .map(SeatEntity::toSeat)
                .toList();
    }
}
