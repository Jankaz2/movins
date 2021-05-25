package model.ticket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.seance.Seance;
import model.seat.Seat;
import model.user.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTicketResponseDto {
    private Long id;
    private Seat seat;
    private Seance seance;
    private User user;
    private Double price;
}
