package kazmierczak.jan.cinema;

import lombok.RequiredArgsConstructor;
import model.cinema.repository.CinemaRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CinemaService {
    private final CinemaRepository cinemaRepository;


}
