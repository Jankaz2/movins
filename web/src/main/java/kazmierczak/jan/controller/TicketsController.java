package kazmierczak.jan.controller;

import kazmierczak.jan.cinema.TicketsService;
import kazmierczak.jan.controller.dto.ResponseDto;
import kazmierczak.jan.model.ticket.dto.CreateTicketDto;
import kazmierczak.jan.model.ticket.dto.CreateTicketResponseDto;
import kazmierczak.jan.model.ticket.dto.GetTicketDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static kazmierczak.jan.controller.dto.ResponseDto.toResponse;
import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/tickets")
public class TicketsController {
    private final TicketsService ticketsService;

    /**
     * @param createTicketDto we want to post
     * @return response dto of posted object
     */
    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseDto<CreateTicketResponseDto> createTicket(@RequestBody CreateTicketDto createTicketDto) {
        return toResponse(ticketsService.createTicket(createTicketDto));
    }

    /**
     * @return all tickets
     */
    @GetMapping
    public ResponseDto<List<GetTicketDto>> getAllTickets() {
        return toResponse(ticketsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseDto<List<GetTicketDto>> getAllTicketsByUserId(@PathVariable Long id) {
        return toResponse(ticketsService.findAllByUserId(id));
    }
}
