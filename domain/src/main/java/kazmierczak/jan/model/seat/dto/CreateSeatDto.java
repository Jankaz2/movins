package kazmierczak.jan.model.seat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import kazmierczak.jan.model.seat.Seat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateSeatDto {
    private Integer row;
    private Integer place;

    /**
     *
     * @return Seat object mapped from SeatDto
     */
    public Seat toSeat() {
        return Seat
                .builder()
                .row(row)
                .place(place)
                .build();
    }
}
