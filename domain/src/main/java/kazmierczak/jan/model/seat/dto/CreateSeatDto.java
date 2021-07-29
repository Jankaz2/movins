package kazmierczak.jan.model.seat.dto;

import kazmierczak.jan.model.seat.Seat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateSeatDto {
    private Integer row;
    private Integer place;
    private Long cinemaRoomId;
    boolean booked;

    /**
     *
     * @return Seat object mapped from SeatDto
     */
    public Seat toSeat() {
        return Seat
                .builder()
                .row(row)
                .place(place)
                .booked(booked)
                .build();
    }
}
