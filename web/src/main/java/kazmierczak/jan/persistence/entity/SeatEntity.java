package kazmierczak.jan.persistence.entity;

import kazmierczak.jan.model.seat.Seat;
import kazmierczak.jan.persistence.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static kazmierczak.jan.model.seat.SeatUtils.*;
import static kazmierczak.jan.persistence.entity.CinemaRoomEntity.fromCinemaRooomtoEntity;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "seats")
public class SeatEntity extends BaseEntity {
    @Column(name = "cinema_row")
    private Integer row;
    private Integer place;

    @ManyToOne(cascade = CascadeType.REMOVE)
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
                .cinemaRoom(cinemaRoom == null ? null : cinemaRoom.toCinemaRoom())
                .tickets(new ArrayList<>())
                .build();
    }

    /**
     *
     * @param seat we want to map
     * @return seat entity object
     */
    public static SeatEntity fromSeatToEntity(Seat seat) {
        if(seat == null) {
            return null;
        }
        var seatId = toSeatId.apply(seat);
        var seatRow = toSeatRow.apply(seat);
        var seatPlace = toSeatPlace.apply(seat);
        var seatCinemaRoom = toSeatCinemaRoom.apply(seat);

        return SeatEntity
                .builder()
                .id(seatId)
                .row(seatRow)
                .place(seatPlace)
                .cinemaRoom(fromCinemaRooomtoEntity(seatCinemaRoom))
                .build();
    }
}
