package kazmierczak.jan.controller;

import kazmierczak.jan.cinema.MovieService;
import kazmierczak.jan.controller.dto.ResponseDto;
import kazmierczak.jan.model.movie.dto.CreateMovieDto;
import kazmierczak.jan.model.movie.dto.CreateMovieResponseDto;
import kazmierczak.jan.model.movie.dto.GetMovieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cinema/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    /**
     *
     * @return list of all movies packed into responsedto
     */
    @GetMapping
    public ResponseDto<List<GetMovieDto>> getAllMovies() {
        return ResponseDto
                .<List<GetMovieDto>>builder()
                .data(movieService.findAll())
                .build();
    }

    /**
     *
     * @param createMovieDto we want to post
     * @return response dto of created object packed into responseDto
     */
    @PostMapping("/{id}")
    public ResponseDto<CreateMovieResponseDto> createMovie(
            @PathVariable Long id,
            @RequestBody CreateMovieDto createMovieDto) {
        return ResponseDto
                .<CreateMovieResponseDto>builder()
                .data(movieService.createMovie(id, createMovieDto))
                .build();
    }
}
