package kazmierczak.jan.cinema;

import kazmierczak.jan.cinema.exception.CinemaServiceException;
import lombok.RequiredArgsConstructor;
import model.cinema.Cinema;
import model.cinema.dto.CreateCinemaDto;
import model.cinema.dto.CreateCinemaResponseDto;
import model.cinema.dto.GetCinemaDto;
import model.cinema.dto.validator.CreateCinemaDtoValidator;
import model.cinema.repository.CinemaRepository;
import model.cinema_room.dto.CreateCinemaRoomDto;
import model.cinema_room.repository.CinemaRoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static config.validator.Validator.*;
import static model.cinema_room.CinemaRoomUtils.*;

@Service
@RequiredArgsConstructor
@Transactional
public class CinemaService {
    private final CinemaRepository cinemaRepository;
    private final CinemaRoomRepository cinemaRoomRepository;

    /**
     * @param createCinemaDto object we want to create
     * @return created CinemaResponseDto object
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public CreateCinemaResponseDto createCinema(CreateCinemaDto createCinemaDto) {
        validate(new CreateCinemaDtoValidator(), createCinemaDto);

        var name = createCinemaDto.getName();
        if (cinemaRepository.findByName(name).isPresent()) {
            throw new CinemaServiceException("Cinema with this name -> [" + name + "] already exists");
        }
        var cinema = createCinemaDto.toCinema();

        var cinemaRooms = createCinemaDto
                .getCinemaRooms()
                .stream()
                .map(CreateCinemaRoomDto::toCinemaRoom)
                .peek(cinemaRoom -> cinemaRoom.setCinema(cinema))
                .toList();

        var insertedCinemaRooms = cinemaRoomRepository.saveAll(cinemaRooms);

        return insertedCinemaRooms
                .stream()
                .findFirst()
                .map(cinemaRoom -> toCinemaRoomCinema.apply(cinemaRoom).toCreateCinemaResponseDto())
                .orElseThrow(() -> new CinemaServiceException("Cannot insert cinema"));
    }

    /**
     * @return list of all cinemas
     */
    public List<GetCinemaDto> findAll() {
        return cinemaRepository
                .findAll()
                .stream()
                .map(Cinema::toGetCinemaDto)
                .toList();
    }

    /**
     * @param id of cinema we want to find
     * @return cinema with id from parameter if it exists, otherwise throws exception
     */
    public GetCinemaDto findById(Long id) {
        return cinemaRepository
                .findById(id)
                .map(Cinema::toGetCinemaDto)
                .orElseThrow(() -> new CinemaServiceException("Cannot find cinema with this id: " + id));
    }

    /**
     * @param id of cinema we want to delete
     * @return deleted object
     */
    public GetCinemaDto deleteById(Long id) {
        return cinemaRepository
                .delete(id)
                .map(Cinema::toGetCinemaDto)
                .orElseThrow(() -> new CinemaServiceException("Cannot find cinema with this id: " + id));
    }
}
