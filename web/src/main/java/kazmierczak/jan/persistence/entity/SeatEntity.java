package kazmierczak.jan.persistence.entity;

import kazmierczak.jan.persistence.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import model.cinemas.Seat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
}
