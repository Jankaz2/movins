package kazmierczak.jan.controller;

import kazmierczak.jan.cinema.CinemaService;
import kazmierczak.jan.controller.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import model.cinema.dto.CreateCinemaDto;
import model.cinema.dto.CreateCinemaResponseDto;
import model.cinema.dto.GetCinemaDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
                .data(cinemaService.createCinemaDto(createCinemaDto))
                .build();
    }
}
