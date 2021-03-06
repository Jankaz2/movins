package kazmierczak.jan.cinema;

import kazmierczak.jan.cinema.exception.MovieServiceException;
import kazmierczak.jan.model.cinema_room.repository.CinemaRoomRepository;
import kazmierczak.jan.model.movie.Movie;
import kazmierczak.jan.model.movie.dto.CreateMovieDto;
import kazmierczak.jan.model.movie.dto.CreateMovieResponseDto;
import kazmierczak.jan.model.movie.dto.GetMovieDto;
import kazmierczak.jan.model.movie.dto.validator.CreateMovieDtoValidator;
import kazmierczak.jan.model.movie.repository.MovieRepository;
import kazmierczak.jan.model.seance.Seance;
import kazmierczak.jan.model.seance.dto.CreateSeanceDto;
import kazmierczak.jan.model.seance.dto.GetSeanceDto;
import kazmierczak.jan.model.seance.repository.SeanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static kazmierczak.jan.config.validator.Validator.validate;
import static kazmierczak.jan.model.seance.SeanceUtils.toSeanceMovie;
import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final SeanceRepository seanceRepository;
    private final CinemaRoomRepository cinemaRoomRepository;

    @Transactional(isolation = SERIALIZABLE)
    public CreateMovieResponseDto createMovie(Long cinemaRoomId, CreateMovieDto createMovieDto) {
        validate(new CreateMovieDtoValidator(), createMovieDto);

        var movie = createMovieDto.toMovie();
        var cinemaRoom = cinemaRoomRepository.findById(cinemaRoomId)
                .orElseThrow(() -> new MovieServiceException("Cannot find cinema room with this id: " + cinemaRoomId));

        var seances = createMovieDto
                .getSeances()
                .stream()
                .map(CreateSeanceDto::toSeance)
                .peek(seance -> seance.setMovie(movie))
                .toList();

        var insertedSeances = seanceRepository.saveAll(seances);
        seanceRepository.saveAll(insertedSeances
                .stream()
                .peek(seance -> seance.setCinemaRoom(cinemaRoom))
                .toList());

        return insertedSeances
                .stream()
                .findFirst()
                .map(seance -> toSeanceMovie.apply(seance).toCreateMovieResponseDto())
                .orElseThrow();
    }

    /**
     * @return list of all movies
     */
    public List<GetMovieDto> findAll() {
        return movieRepository
                .findAll()
                .stream()
                .map(Movie::toGetMovieDto)
                .toList();
    }

    /**
     * @param id of cinema room we want to find seances by
     * @return list of all seances
     */
    public List<GetSeanceDto> findAllSeancesByCinemaRoomId(Long id) {
        return seanceRepository
                .findAllByCinemaRoomId(id)
                .stream()
                .map(Seance::toGetSeanceDto)
                .toList();
    }
}
