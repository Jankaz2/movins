package kazmierczak.jan.controller;

import kazmierczak.jan.cinema.TicketsService;
import kazmierczak.jan.controller.dto.ResponseDto;
import kazmierczak.jan.model.ticket.dto.CreateTicketDto;
import kazmierczak.jan.model.ticket.dto.CreateTicketResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static kazmierczak.jan.controller.dto.ResponseDto.*;

@CrossOrigin
@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketsController {
    private final TicketsService ticketsService;

    /**
     *
     * @param createTicketDto we want to post
     * @return response dto of posted object
     */
    @PostMapping
    public ResponseDto<CreateTicketResponseDto> createTicket(@RequestBody CreateTicketDto createTicketDto) {
        return toResponse(ticketsService.createTicket(createTicketDto));
    }
}
