package kazmierczak.jan.persistence.entity;

import kazmierczak.jan.persistence.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import kazmierczak.jan.model.seat.Seat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static kazmierczak.jan.persistence.entity.TicketEntity.*;
import static kazmierczak.jan.model.seat.SeatUtils.*;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "seats")
public class SeatEntity extends BaseEntity {
    @Column(name = "cinema_row")
    private Integer row;
    private Integer place;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "cinemaroom_id")
    private CinemaRoomEntity cinemaRoom;

    @OneToMany(mappedBy = "seat")
    @Builder.Default
    private List<TicketEntity> tickets = new ArrayList<>();

    /**
     * @return Seat object mapped from SeatEntity
     */
    public Seat toSeat() {
        return Seat
                .builder()
                .id(id)
                .row(row)
                .place(place)
                .cinemaRoom(cinemaRoom.toCinemaRoom())
                .tickets(new ArrayList<>())
                .build();
    }

    /**
     *
     * @param cinemaRoomSeats list we want to map
     * @return list of seat entity objects
     */
    public static List<SeatEntity> fromSeatListToEntityList(List<Seat> cinemaRoomSeats) {
        return cinemaRoomSeats
                .stream()
                .map(SeatEntity::fromSeatToEntity)
                .toList();
    }

    /**
     *
     * @param seat we want to map
     * @return seat entity object
     */
    public static SeatEntity fromSeatToEntity(Seat seat) {
        var seatId = toSeatId.apply(seat);
        var seatRow = toSeatRow.apply(seat);
        var seatPlace = toSeatPlace.apply(seat);
        var seatCinemaRoom = toSeatCinemaRoom.apply(seat);
        var seatTickets= toSeatTicket.apply(seat);

        return SeatEntity
                .builder()
                .id(seatId)
                .row(seatRow)
                .place(seatPlace)
               // .cinemaRoom(fromCinemaRooomtoEntity(seatCinemaRoom))
                .tickets(fromTicketsToEntityList(seatTickets))
                .build();
    }
}
