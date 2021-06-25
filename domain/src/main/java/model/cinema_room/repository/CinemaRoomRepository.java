package model.cinema_room.repository;

import config.repository.CrudRepository;
import model.cinema_room.CinemaRoom;

import java.util.List;

public interface CinemaRoomRepository extends CrudRepository<CinemaRoom, Long> {
    List<CinemaRoom> saveAll(List<CinemaRoom> cinemaRooms);
}
