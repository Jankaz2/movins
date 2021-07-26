package kazmierczak.jan.cinema;

import kazmierczak.jan.cinema.exception.TicketsServiceException;
import kazmierczak.jan.model.cinema_room.repository.CinemaRoomRepository;
import kazmierczak.jan.model.seance.repository.SeanceRepository;
import kazmierczak.jan.model.ticket.dto.CreateTicketDto;
import kazmierczak.jan.model.ticket.dto.CreateTicketDtoValidator;
import kazmierczak.jan.model.ticket.dto.CreateTicketResponseDto;
import kazmierczak.jan.model.ticket.repository.TicketRepository;
import kazmierczak.jan.model.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import static kazmierczak.jan.config.validator.Validator.validate;

@Service
@RequiredArgsConstructor
public class TicketsService {
    private final TicketRepository ticketRepository;
    private final SeanceRepository seanceRepository;
    private final CinemaRoomRepository cinemaRoomRepository;
    private final UserRepository userRepository;

    /**
     *
     * @param createTicketDto we want to create
     * @return response dto of added object
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public CreateTicketResponseDto createTicket(CreateTicketDto createTicketDto) {
        if (createTicketDto == null) {
            throw new TicketsServiceException("Ticket dto object is null");
        }

        validate(new CreateTicketDtoValidator(), createTicketDto);

        var cinemaRoomId = createTicketDto.getSeat().getCinemaRoomId();
        var seanceId = createTicketDto.getSeanceId();
        var userId = createTicketDto.getUserId();
        var seat = createTicketDto.getSeat().toSeat();
        var ticket = createTicketDto.toTicket();

        var user = userRepository
                .findById(userId)
                .orElseThrow(() -> new TicketsServiceException("Cannot find user with this id: " + userId));

        var cinemaRoom = cinemaRoomRepository
                .findById(cinemaRoomId)
                .orElseThrow(() -> new TicketsServiceException("Cannot find cinemaRoom with this id: " + cinemaRoomId));

        var seance = seanceRepository
                .findById(seanceId)
                .orElseThrow(() -> new TicketsServiceException("Cannot find seance with this id: " + seanceId));

        seat.setCinemaRoom(cinemaRoom);
        ticket.setSeat(seat);

        var insertedTicket = ticketRepository
                .add(ticket)
                .orElseThrow(() -> new TicketsServiceException("Cannot insert ticket"));

        insertedTicket.setUser(user);
        insertedTicket.setSeance(seance);

        ticketRepository.add(insertedTicket);

        return insertedTicket.toCreateTicketResponseDto();
    }
}
