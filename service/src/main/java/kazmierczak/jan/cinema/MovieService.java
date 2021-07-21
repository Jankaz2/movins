package kazmierczak.jan.cinema;

import kazmierczak.jan.cinema.exception.MovieServiceException;
import kazmierczak.jan.config.validator.Validator;
import kazmierczak.jan.model.cinema_room.repository.CinemaRoomRepository;
import kazmierczak.jan.model.movie.Movie;
import kazmierczak.jan.model.movie.dto.CreateMovieDto;
import kazmierczak.jan.model.movie.dto.CreateMovieResponseDto;
import kazmierczak.jan.model.movie.dto.GetMovieDto;
import kazmierczak.jan.model.movie.dto.validator.CreateMovieDtoValidator;
import kazmierczak.jan.model.movie.repository.MovieRepository;
import kazmierczak.jan.model.seance.SeanceUtils;
import kazmierczak.jan.model.seance.dto.CreateSeanceDto;
import kazmierczak.jan.model.seance.dto.CreateSeanceResponseDto;
import kazmierczak.jan.model.seance.repository.SeanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static kazmierczak.jan.config.validator.Validator.*;
import static kazmierczak.jan.model.seance.SeanceUtils.*;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final SeanceRepository seanceRepository;
    private final CinemaRoomRepository cinemaRoomRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public CreateMovieResponseDto createMovie(Long cinemaRoomId, CreateMovieDto createMovieDto) {
        var title = createMovieDto.getTitle();
        if (movieRepository.findByTitle(title).isPresent()) {
            throw new MovieServiceException("Movie with this title: " + title + " already exists");
        }

        validate(new CreateMovieDtoValidator(), createMovieDto);

        var movie = createMovieDto.toMovie();
        var cinemaRoom = cinemaRoomRepository.findById(cinemaRoomId)
                .orElseThrow(() -> new MovieServiceException("Cannot find cinema room with this id: " + cinemaRoomId));

        var seances = createMovieDto
                .getSeances()
                .stream()
                .map(CreateSeanceDto::toSeance)
                .peek(seance -> seance.setMovie(movie))
                .peek(seance -> seance.setCinemaRoom(cinemaRoom))
                .toList();

        var insertedSeances = seanceRepository.saveAll(seances);

        return insertedSeances
                .stream()
                .findFirst()
                .map(seance -> toSeanceMovie.apply(seance).toCreateMovieResponseDto())
                .orElseThrow();
    }

    /**
     *
     * @return list of all movies
     */
    public List<GetMovieDto> findAll() {
        return movieRepository
                .findAll()
                .stream()
                .map(Movie::toGetMovieDto)
                .toList();
    }
}
