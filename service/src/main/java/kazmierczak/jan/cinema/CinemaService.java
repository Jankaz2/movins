package kazmierczak.jan.cinema;

import kazmierczak.jan.cinema.exception.CinemaServiceException;
import kazmierczak.jan.model.cinema.CinemaUtils;
import kazmierczak.jan.model.cinema_room.CinemaRoom;
import kazmierczak.jan.model.cinema_room.dto.CreateCinemaRoomResponseDto;
import kazmierczak.jan.model.cinema_room.dto.validator.CreateCinemaRoomDtoValidator;
import lombok.RequiredArgsConstructor;
import kazmierczak.jan.model.cinema.Cinema;
import kazmierczak.jan.model.cinema.dto.CreateCinemaDto;
import kazmierczak.jan.model.cinema.dto.CreateCinemaResponseDto;
import kazmierczak.jan.model.cinema.dto.GetCinemaDto;
import kazmierczak.jan.model.cinema.dto.validator.CreateCinemaDtoValidator;
import kazmierczak.jan.model.cinema.repository.CinemaRepository;
import kazmierczak.jan.model.cinema_room.dto.CreateCinemaRoomDto;
import kazmierczak.jan.model.cinema_room.repository.CinemaRoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static kazmierczak.jan.config.validator.Validator.*;
import static kazmierczak.jan.model.cinema.CinemaUtils.*;
import static kazmierczak.jan.model.cinema_room.CinemaRoomUtils.*;

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
    //TODO: przetestuj
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
     * @param cinemaId       id of cinema we want to add cinema rooms to
     * @param cinemaRoomDtos cinema rooms we want to add
     * @return cinema response dto object
     */
    public CreateCinemaResponseDto changeCinemaRooms(Long cinemaId, List<CreateCinemaRoomDto> cinemaRoomDtos) {
        cinemaRoomDtos.forEach(createCinemaRoomDto -> validate(new CreateCinemaRoomDtoValidator(), createCinemaRoomDto));

        if (cinemaRepository.findById(cinemaId).isEmpty()) {
            throw new CinemaServiceException("Cinema with this id -> [" + cinemaId + "] does not exist");
        }

        var cinema = cinemaRepository
                .findById(cinemaId).orElseThrow(() -> new CinemaServiceException("Cannot find cinema"));

        var cinemaRooms = cinemaRoomDtos
                .stream()
                .map(CreateCinemaRoomDto::toCinemaRoom)
                .toList();

        var changedCinema = cinema.withChangedCinemaRooms(cinemaRooms);

        var finalCinemaRooms = cinemaToCinemaRooms
                .apply(changedCinema)
                .stream()
                .peek(cinemaRoom -> cinemaRoom.setCinema(changedCinema))
                .toList();

        cinemaRoomRepository.saveAll(finalCinemaRooms);

        return changedCinema.toCreateCinemaResponseDto();
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
