package kazmierczak.jan.model.ticket.dto;

import kazmierczak.jan.model.seance.dto.GetSeanceDto;
import kazmierczak.jan.model.seat.dto.GetSeatDto;
import kazmierczak.jan.model.user.dto.GetUserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetTicketDto {
    private Long id;
    private GetSeanceDto seance;
    private GetUserDto user;
    private GetSeatDto seat;
    private Double price;
}
