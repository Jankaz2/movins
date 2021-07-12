package kazmierczak.jan.persistence.repository;

import kazmierczak.jan.persistence.dao.CinemaRoomEntityDao;
import kazmierczak.jan.persistence.entity.CinemaRoomEntity;
import lombok.RequiredArgsConstructor;
import kazmierczak.jan.model.cinema_room.CinemaRoom;
import kazmierczak.jan.model.cinema_room.repository.CinemaRoomRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static kazmierczak.jan.persistence.entity.CinemaRoomEntity.*;

@Repository
@RequiredArgsConstructor
public class CinemaRoomRepositoryImpl implements CinemaRoomRepository {
    private final CinemaRoomEntityDao cinemaRoomEntityDao;

    @Override
    public Optional<CinemaRoom> add(CinemaRoom cinemaRoom) {
        var cinemaRoomEntity = fromCinemaRooomtoEntity(cinemaRoom);
        var insertedCinemaRoom = cinemaRoomEntityDao.save(cinemaRoomEntity);
        return Optional.ofNullable(insertedCinemaRoom.toCinemaRoom());
    }

    @Override
    public Optional<CinemaRoom> delete(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Optional<CinemaRoom> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<CinemaRoom> findAll() {
        return null;
    }

    @Override
    public List<CinemaRoom> findAllById(List<Long> ids) {
        return null;
    }

    @Override
    public List<CinemaRoom> saveAll(List<CinemaRoom> cinemaRooms) {
        var cinemaRoomsEntities = fromCinemaRoomsToEntityList(cinemaRooms);

        var insertedCinemaRooms = cinemaRoomEntityDao
                .saveAll(cinemaRoomsEntities);

        return insertedCinemaRooms
                .stream()
                .map(CinemaRoomEntity::toCinemaRoom)
                .toList();
    }
}
