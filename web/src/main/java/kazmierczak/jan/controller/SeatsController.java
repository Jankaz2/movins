package kazmierczak.jan.controller;

import kazmierczak.jan.cinema.SeatsService;
import kazmierczak.jan.controller.dto.ResponseDto;
import kazmierczak.jan.model.seat.dto.GetSeatDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static kazmierczak.jan.controller.dto.ResponseDto.*;

@CrossOrigin
@RestController
@RequestMapping("/cinema/seats")
@RequiredArgsConstructor
public class SeatsController {
    private final SeatsService seatsService;

    /**
     * @return list of all seats packed into response dto object
     */
    @GetMapping
    public ResponseDto<List<GetSeatDto>> getAllSeats() {
        return toResponse(seatsService.findAll());
    }
}
