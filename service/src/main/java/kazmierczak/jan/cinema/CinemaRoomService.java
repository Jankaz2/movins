package kazmierczak.jan.cinema;

import kazmierczak.jan.cinema.exception.CinemaRoomServiceException;
import kazmierczak.jan.model.cinema.dto.GetCinemaDto;
import kazmierczak.jan.model.cinema_room.CinemaRoom;
import kazmierczak.jan.model.cinema_room.dto.GetCinemaRoomDto;
import kazmierczak.jan.model.cinema_room.repository.CinemaRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CinemaRoomService {
    private final CinemaRoomRepository cinemaRoomRepository;

    /**
     *
     * @param id of cinema room we want to find
     * @return object of found cinema room
     */
    public GetCinemaRoomDto findById(Long id) {
        return cinemaRoomRepository
                .findById(id)
                .map(CinemaRoom::toGetCinemaRoomDto)
                .orElseThrow(() -> new CinemaRoomServiceException("Cannot find cinema room with this id: " + id));
    }

    /**
     * @return list of all CinemaRooms objects
     */
    public List<GetCinemaRoomDto> findALl() {
        return cinemaRoomRepository
                .findAll()
                .stream()
                .map(CinemaRoom::toGetCinemaRoomDto)
                .toList();
    }

    /**
     *
     * @param id of cinema we want to find cinema rooms by
     * @return list of cinema rooms
     */
    public List<GetCinemaRoomDto> findByCinemaId(Long id) {
        return cinemaRoomRepository
                .findCinemaRoomsByCinemaId(id)
                .stream()
                .map(CinemaRoom::toGetCinemaRoomDto)
                .toList();
    }
}
