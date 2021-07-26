package kazmierczak.jan.controller;

import kazmierczak.jan.cinema.CinemaRoomService;
import kazmierczak.jan.cinema.CinemaService;
import kazmierczak.jan.controller.dto.ResponseDto;
import kazmierczak.jan.model.cinema.dto.CreateCinemaDto;
import kazmierczak.jan.model.cinema.dto.CreateCinemaResponseDto;
import kazmierczak.jan.model.cinema.dto.GetCinemaDto;
import kazmierczak.jan.model.cinema_room.dto.CreateCinemaRoomDto;
import kazmierczak.jan.model.cinema_room.dto.GetCinemaRoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static kazmierczak.jan.controller.dto.ResponseDto.*;

@CrossOrigin
@RestController
@RequestMapping("/cinema")
@RequiredArgsConstructor
public class CinemaController {
    private final CinemaService cinemaService;
    private final CinemaRoomService cinemaRoomService;

    /**
     * @return list of all cinemas
     */
    @GetMapping
    public ResponseDto<List<GetCinemaDto>> getAllCinemas() {
        return toResponse(cinemaService.findAll());
    }

    /**
     * @param createCinemaDto object we want to post
     * @return response dto of posted cinema object
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<CreateCinemaResponseDto> createCinema(@RequestBody CreateCinemaDto createCinemaDto) {
        return toResponse(cinemaService.createCinema(createCinemaDto));
    }

    /**
     * @param id we want to find cinema by
     * @return cinemaDto with this id
     */
    @GetMapping("/{id}")
    public ResponseDto<GetCinemaDto> getCinemaById(@PathVariable Long id) {
        return toResponse(cinemaService.findById(id));
    }

    /**
     * @param id of object we want to delete
     * @return deleted object
     */
    @DeleteMapping("/{id}")
    public ResponseDto<GetCinemaDto> deleteCinema(@PathVariable Long id) {
        return toResponse(cinemaService.deleteById(id));
    }

    /**
     * @param name                of cinema we want to add cinema room to
     * @param createCinemaRoomDto the cinema room we want to add
     * @return response dto of added object
     */
    @PatchMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<CreateCinemaResponseDto> addCinemaRoomToCinema(
            @PathVariable String name,
            @RequestBody List<CreateCinemaRoomDto> createCinemaRoomDto) {
        return toResponse(cinemaService.addCinemaRoomsToExistedCinema(name, createCinemaRoomDto));
    }

    /**
     * @param oldName         name we want to find cinema by
     * @param createCinemaDto new cinema object we want to set
     * @return response dto object with id of updated cinema
     */
    @PutMapping("/{oldName}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<CreateCinemaResponseDto> updateCinema(
            @PathVariable String oldName,
            @RequestBody CreateCinemaDto createCinemaDto) {
        return toResponse(cinemaService.updateCinema(oldName, createCinemaDto));
    }

    /**
     * @return list of all cinema rooms
     */
    @GetMapping("/rooms")
    public ResponseDto<List<GetCinemaRoomDto>> getAllCinemaRooms() {
        return toResponse(cinemaRoomService.findAll());
    }

    /**
     * @param id of cinema room we want to find
     * @return cinema room with this id
     */
    @GetMapping("/rooms/{id}")
    public ResponseDto<GetCinemaRoomDto> getCinemaRoomById(@PathVariable Long id) {
        return toResponse(cinemaRoomService.findById(id));
    }

    /**
     * @param id we want to find cinema rooms by
     * @return list of cinema rooms
     */
    @GetMapping("/rooms/name/{id}")
    public ResponseDto<List<GetCinemaRoomDto>> getCinemaRoomsByCinemaId(@PathVariable Long id) {
        return toResponse(cinemaRoomService.findByCinemaId(id));
    }
}
