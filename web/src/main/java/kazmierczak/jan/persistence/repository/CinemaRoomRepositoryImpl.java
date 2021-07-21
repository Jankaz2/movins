package kazmierczak.jan.persistence.repository;

import kazmierczak.jan.persistence.dao.CinemaRoomEntityDao;
import kazmierczak.jan.persistence.entity.CinemaRoomEntity;
import kazmierczak.jan.persistence.exception.PersistenceException;
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

    /**
     *
     * @param cinemaRoom we want to add
     * @return added object
     */
    @Override
    public Optional<CinemaRoom> add(CinemaRoom cinemaRoom) {
        var cinemaRoomEntity = fromCinemaRooomtoEntity(cinemaRoom);
        var insertedCinemaRoom = cinemaRoomEntityDao.save(cinemaRoomEntity);
        return Optional.ofNullable(insertedCinemaRoom.toCinemaRoom());
    }

    /**
     *
     * @param id of cinema room we want to delete
     * @return deleted object
     */
    @Override
    public Optional<CinemaRoom> delete(Long id) {
        var cinemaRoomEntity = cinemaRoomEntityDao
                .findById(id)
                .orElseThrow(() -> new PersistenceException("Cannot find cinema room with this id -> " + id));

        cinemaRoomEntityDao.delete(cinemaRoomEntity);
        return Optional.ofNullable(cinemaRoomEntity.toCinemaRoom());
    }

    /**
     *
     * @param id of cinema room we want to find
     * @return found cinema
     */
    @Override
    public Optional<CinemaRoom> findById(Long id) {
        return cinemaRoomEntityDao
                .findById(id)
                .map(CinemaRoomEntity::toCinemaRoom);
    }

    /**
     *
     * @return list of all cinema rooms
     */
    @Override
    public List<CinemaRoom> findAll() {
        return cinemaRoomEntityDao
                .findAll()
                .stream()
                .map(CinemaRoomEntity::toCinemaRoom)
                .toList();
    }

    /**
     *
     * @param ids we want to find cinema rooms by
     * @return list of found cinema rooms
     */
    @Override
    public List<CinemaRoom> findAllById(List<Long> ids) {
        return cinemaRoomEntityDao
                .findAllById(ids)
                .stream()
                .map(CinemaRoomEntity::toCinemaRoom)
                .toList();
    }

    /**
     *
     * @param cinemaRooms we want to save
     * @return list of saved cinema rooms
     */
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
