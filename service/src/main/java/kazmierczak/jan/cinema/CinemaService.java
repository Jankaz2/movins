package kazmierczak.jan.cinema;

import kazmierczak.jan.cinema.exception.CinemaServiceException;
import kazmierczak.jan.model.cinema_room.CinemaRoom;
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

import java.util.ArrayList;
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
        var name = createCinemaDto.getName();
        if (cinemaRepository.findByName(name).isPresent()) {
            throw new CinemaServiceException("Cinema with this name -> [" + name + "] already exists");
        }

        validate(new CreateCinemaDtoValidator(), createCinemaDto);
        var cinema = createCinemaDto.toCinema();

        var cinemaRooms = createCinemaDto
                .getCinemaRooms()
                .stream()
                .map(CreateCinemaRoomDto::toCinemaRoom)
                .peek(cinemaRoom -> cinemaRoom.setCinema(cinema))
                .toList();

        var insertedCinemaRooms = cinemaRoomRepository.saveAll(cinemaRooms);

        return //cinema.toCreateCinemaResponseDto();
                insertedCinemaRooms
                        .stream()
                        .findFirst()
                        .map(cinemaRoom -> toCinemaRoomCinema.apply(cinemaRoom).toCreateCinemaResponseDto())
                        .orElseThrow(() -> new CinemaServiceException("Cannot insert cinema"));
    }

    /**
     * @param cinemaName     id of cinema we want to add cinema rooms to
     * @param cinemaRoomDtos cinema rooms we want to add
     * @return cinema response dto object
     */
    public CreateCinemaResponseDto addCinemaRoomsToExistedCinema(String cinemaName, List<CreateCinemaRoomDto> cinemaRoomDtos) {
        if (cinemaRepository.findByName(cinemaName).isEmpty()) {
            throw new CinemaServiceException("Cinema with this id -> [" + cinemaName + "] does not exist");
        }

        cinemaRoomDtos.forEach(createCinemaRoomDto -> validate(new CreateCinemaRoomDtoValidator(), createCinemaRoomDto));

        var cinema = cinemaRepository
                .findByName(cinemaName).orElseThrow(() -> new CinemaServiceException("Cannot find cinema"));

        var cinemaRooms = cinemaRoomDtos
                .stream()
                .map(CreateCinemaRoomDto::toCinemaRoom)
                .toList();

        var changedCinema = cinema.withAddedCinemaRooms(cinemaRooms);

        var finalCinemaRooms = cinemaToCinemaRooms
                .apply(changedCinema)
                .stream()
                .peek(cinemaRoom -> cinemaRoom.setCinema(changedCinema))
                .toList();

        cinemaRoomRepository.saveAll(finalCinemaRooms);

        return changedCinema.toCreateCinemaResponseDto();
    }

    /**
     * @param oldName         name we want to find existing cinema by
     * @param createCinemaDto cinema we want to set as a new one
     * @return response of cinema dto
     */
    @Transactional
    public CreateCinemaResponseDto updateCinema(String oldName, CreateCinemaDto createCinemaDto) {
        if (cinemaRepository.findByName(oldName).isEmpty()) {
            throw new CinemaServiceException("Cinema with this name: " + oldName + " does not exist");
        }

        validate(new CreateCinemaDtoValidator(), createCinemaDto);

        int cinemaDtoCinemaRoomIndex = 0;
        var cinemaToUpdate = cinemaRepository.findByName(oldName)
                .orElseThrow(() -> new CinemaServiceException("Cannot find cinema with this name: " + oldName));

        var cinemaRooms = cinemaToCinemaRooms.apply(cinemaToUpdate);
        var updatedCinema = cinemaToUpdate.withChangedData(createCinemaDto);

        var address = cinemaToAddress.apply(cinemaToUpdate).withChangedData(createCinemaDto.getAddress());
        updatedCinema.setAddress(address);

        var finalCinemaRooms = new ArrayList<CinemaRoom>();

        for (var finalCinemaRoom : cinemaRooms) {
            finalCinemaRoom.setCinema(updatedCinema);
            finalCinemaRooms.add(finalCinemaRoom.withChangedData(createCinemaDto.getCinemaRooms().get(cinemaDtoCinemaRoomIndex++)));
        }

        cinemaRoomRepository.saveAll(finalCinemaRooms);
        return updatedCinema.toCreateCinemaResponseDto();
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
