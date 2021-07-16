package kazmierczak.jan.controller;

import kazmierczak.jan.cinema.CinemaService;
import kazmierczak.jan.controller.dto.ResponseDto;
import kazmierczak.jan.model.cinema_room.dto.CreateCinemaRoomDto;
import lombok.RequiredArgsConstructor;
import kazmierczak.jan.model.cinema.dto.CreateCinemaDto;
import kazmierczak.jan.model.cinema.dto.CreateCinemaResponseDto;
import kazmierczak.jan.model.cinema.dto.GetCinemaDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/cinema")
@RequiredArgsConstructor
public class CinemaController {
    private final CinemaService cinemaService;

    /**
     * @return list of all cinemas
     */
    @GetMapping
    public ResponseDto<List<GetCinemaDto>> getAllCinemas() {
        return ResponseDto
                .<List<GetCinemaDto>>builder()
                .data(cinemaService.findAll())
                .build();
    }

    /**
     * @param createCinemaDto object we want to post
     * @return response dto of posted cinema object
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<CreateCinemaResponseDto> createCinema(@RequestBody CreateCinemaDto createCinemaDto) {
        return ResponseDto
                .<CreateCinemaResponseDto>builder()
                .data(cinemaService.createCinema(createCinemaDto))
                .build();
    }

    /**
     * @param id we want to find cinema by
     * @return cinemaDto with this id
     */
    @GetMapping("/{id}")
    public ResponseDto<GetCinemaDto> getCinemaById(@PathVariable Long id) {
        return ResponseDto
                .<GetCinemaDto>builder()
                .data(cinemaService.findById(id))
                .build();
    }

    /**
     * @param id of object we want to delete
     * @return deleted object
     */
    @DeleteMapping("/{id}")
    public ResponseDto<GetCinemaDto> deleteCinema(@PathVariable Long id) {
        return ResponseDto
                .<GetCinemaDto>builder()
                .data(cinemaService.deleteById(id))
                .build();
    }

    /**
     * @param name of cinema we want to add cinema room to
     * @param createCinemaRoomDto the cinema room we want to add
     * @return response dto of added object
     */
    @PatchMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<CreateCinemaResponseDto> addCinemaRoomToCinema(
            @PathVariable String name,
            @RequestBody List<CreateCinemaRoomDto> createCinemaRoomDto) {

        return ResponseDto
                .<CreateCinemaResponseDto>builder()
                .data(cinemaService.addCinemaRoomsToExistedCinema(name, createCinemaRoomDto))
                .build();
    }

    /**
     *
     * @param oldName name we want to find cinema by
     * @param createCinemaDto new cinema object we want to set
     * @return response dto object with id of updated cinema
     */
    @PutMapping("/{oldName}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<CreateCinemaResponseDto> updateCinema(
            @PathVariable String oldName,
            @RequestBody CreateCinemaDto createCinemaDto) {

        return ResponseDto
                .<CreateCinemaResponseDto>builder()
                .data(cinemaService.updateCinema(oldName, createCinemaDto))
                .build();
    }
}
