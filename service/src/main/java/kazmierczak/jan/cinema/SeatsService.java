package kazmierczak.jan.cinema;

import kazmierczak.jan.model.seat.Seat;
import kazmierczak.jan.model.seat.dto.GetSeatDto;
import kazmierczak.jan.model.seat.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatsService {
    private final SeatRepository seatRepository;

    /**
     *
     * @return list of all seats
     */
    public List<GetSeatDto> findAll() {
        return seatRepository
                .findAll()
                .stream()
                .map(Seat::toGetSeatDto)
                .toList();
    }
}
