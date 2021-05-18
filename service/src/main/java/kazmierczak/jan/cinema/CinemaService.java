package kazmierczak.jan.cinema;

import kazmierczak.jan.cinema.exception.CinemaServiceException;
import lombok.RequiredArgsConstructor;
import model.cinema.Cinema;
import model.cinema.dto.CreateCinemaDto;
import model.cinema.dto.CreateCinemaResponseDto;
import model.cinema.dto.GetCinemaDto;
import model.cinema.dto.validator.CreateCinemaDtoValidator;
import model.cinema.repository.CinemaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static config.validator.Validator.*;

@Service
@RequiredArgsConstructor
@Transactional
public class CinemaService {
    private final CinemaRepository cinemaRepository;

    /**
     * @param createCinemaDto object we want to create
     * @return created CinemaResponseDto object
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public CreateCinemaResponseDto createCinemaDto(CreateCinemaDto createCinemaDto) {
        validate(new CreateCinemaDtoValidator(), createCinemaDto);

        var name = createCinemaDto.getName();
        if (cinemaRepository.findByName(name).isPresent()) {
            throw new CinemaServiceException("Cinema with this name " + name + " already exists");
        }

        var cinema = createCinemaDto.toCinema();
        return cinemaRepository
                .add(cinema)
                .map(Cinema::toCreateCinemaResponseDto)
                .orElseThrow(() -> new CinemaServiceException("Cannot create new cinema"));
    }

    /**
     *
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
     *
     * @param id of cinema we want to find
     * @return cinema with id from parameter if it exists, otherwise throws exception
     */
    public GetCinemaDto findById(Long id) {
        return cinemaRepository
                .findById(id)
                .map(Cinema::toGetCinemaDto)
                .orElseThrow(() -> new CinemaServiceException("Cannot find cinema with this id"));
    }
}
