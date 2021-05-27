package kazmierczak.jan.persistence.entity;

import kazmierczak.jan.persistence.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import model.cinema_room.CinemaRoom;
import model.cinema_room.CinemaRoomUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static kazmierczak.jan.persistence.entity.CinemaEntity.*;
import static kazmierczak.jan.persistence.entity.SeatEntity.*;
import static model.cinema_room.CinemaRoomUtils.*;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "cinema_rooms")
public class CinemaRoomEntity extends BaseEntity {
    private String name;

    @Column(name = "cinema_rows")
    private Integer rows;
    private Integer places;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "cinema_id")
    private CinemaEntity cinema;

    @OneToMany(mappedBy = "cinemaRoom")
    @Builder.Default
    private List<SeatEntity> seats = new ArrayList<>();

    @OneToMany(mappedBy = "cinemaRoom")
    @Builder.Default
    private List<SeanceEntity> seances = new ArrayList<>();

    /**
     * @return CinemaRoom object mapped from CinemaRoomEntity
     */
    public CinemaRoom toCinemaRoom() {
        return CinemaRoom
                .builder()
                .id(id)
                .name(name)
                .rows(rows)
                .cinema(cinema.toCinema())
                .places(places)
                .seats(new ArrayList<>())
                .build();
    }

    /**
     *
     * @param cinemaRooms list we want to map
     * @return list of cinema rooms entity objects
     */
    public static List<CinemaRoomEntity> fromCinemaRoomsToEntityList(List<CinemaRoom> cinemaRooms) {
        return cinemaRooms
                .stream()
                .map(CinemaRoomEntity::fromCinemaRooomtoEntity)
                .toList();
    }

    /**
     *
     * @param cinemaRoom we want to map
     * @return cinema room entity object
     */
    public static CinemaRoomEntity fromCinemaRooomtoEntity(CinemaRoom cinemaRoom) {
        var cinemaRoomId = toCinemaRoomId.apply(cinemaRoom);
        var cinemaRoomName = toCinemaRoomName.apply(cinemaRoom);
        var cinemaRoomRows = toCinemaRoomRows.apply(cinemaRoom);
        var cinemaRoomCinema = toCinemaRoomCinema.apply(cinemaRoom);
        var cinemaRoomPlaces = toCinemaRoomPlaces.apply(cinemaRoom);
        var cinemaRoomSeats = toCinemaRoomSeats.apply(cinemaRoom);

        return CinemaRoomEntity
                .builder()
                .id(cinemaRoomId)
                .name(cinemaRoomName)
                .rows(cinemaRoomRows)
                .cinema(fromCinemaToEntity(cinemaRoomCinema))
                .places(cinemaRoomPlaces)
                .seats(new ArrayList<>())
                .build();
    }
}
