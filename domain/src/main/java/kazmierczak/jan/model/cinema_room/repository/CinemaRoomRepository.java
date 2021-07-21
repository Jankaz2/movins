package kazmierczak.jan.model.cinema_room.repository;

import kazmierczak.jan.config.repository.CrudRepository;
import kazmierczak.jan.model.cinema_room.CinemaRoom;

import java.util.List;

public interface CinemaRoomRepository extends CrudRepository<CinemaRoom, Long> {
    List<CinemaRoom> saveAll(List<CinemaRoom> cinemaRooms);
    List<CinemaRoom> findCinemaRoomsByCinemaName(String name);
}
