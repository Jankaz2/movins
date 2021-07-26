package kazmierczak.jan.model.ticket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import kazmierczak.jan.model.seance.Seance;
import kazmierczak.jan.model.seat.Seat;
import kazmierczak.jan.model.user.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTicketResponseDto {
    private Long id;
}
