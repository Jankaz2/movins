package kazmierczak.jan.persistence.entity;

import kazmierczak.jan.persistence.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import kazmierczak.jan.model.cinema_room.CinemaRoom;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static kazmierczak.jan.persistence.entity.CinemaEntity.*;
import static kazmierczak.jan.model.cinema_room.CinemaRoomUtils.*;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "cinema_rooms")
public class CinemaRoomEntity extends BaseEntity {
    private String name;

    @Column(name = "cinema_rows")
    private Integer rows;

    @Column(name = "cinema_places")
    private Integer places;

    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "cinema_id")
    private CinemaEntity cinema;

    @OneToMany(mappedBy = "cinemaRoom", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @Builder.Default
    private List<SeatEntity> seats = new ArrayList<>();

    @OneToMany(mappedBy = "cinemaRoom", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
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
                .cinema(cinema == null ? null : cinema.toCinema())
                .places(places)
                .seats(new ArrayList<>())
                .build();
    }

    public CinemaRoom toCinemaRoomLight() {
        return CinemaRoom
                .builder()
                .id(id)
                .name(name)
                .rows(rows)
                .places(places)
                .seats(new ArrayList<>())
                .build();
    }

    /**
     * @param cinemaRoom we want to map
     * @return cinema room entity object
     */
    public static CinemaRoomEntity fromCinemaRooomtoEntity(CinemaRoom cinemaRoom) {
        var cinemaRoomId = toCinemaRoomId.apply(cinemaRoom);
        var cinemaRoomName = toCinemaRoomName.apply(cinemaRoom);
        var cinemaRoomRows = toCinemaRoomRows.apply(cinemaRoom);
        var cinemaRoomCinema = toCinemaRoomCinema.apply(cinemaRoom);
        var cinemaRoomPlaces = toCinemaRoomPlaces.apply(cinemaRoom);

        return CinemaRoomEntity
                .builder()
                .id(cinemaRoomId)
                .name(cinemaRoomName)
                .rows(cinemaRoomRows)
                .cinema(fromCinemaToEntity(cinemaRoomCinema))
                .places(cinemaRoomPlaces)
                .build();
    }

    /**
     * @param cinemaRooms list we want to map to entity
     * @return list of cinema rooms entities
     */
    public static List<CinemaRoomEntity> fromCinemaRoomsToEntityList(List<CinemaRoom> cinemaRooms) {
        var cinemaEntity = fromCinemaToEntity(
                toCinemaRoomCinema
                        .apply(cinemaRooms
                                .stream()
                                .findFirst()
                                .orElseThrow()
                        )
        );

        var resultList = new ArrayList<CinemaRoomEntity>();
        for (var cinemaRoom : cinemaRooms) {
            var cinemaRoomId = toCinemaRoomId.apply(cinemaRoom);
            var cinemaRoomName = toCinemaRoomName.apply(cinemaRoom);
            var cinemaRoomRows = toCinemaRoomRows.apply(cinemaRoom);
            var cinemaRoomPlaces = toCinemaRoomPlaces.apply(cinemaRoom);

            resultList.add(CinemaRoomEntity
                    .builder()
                    .id(cinemaRoomId)
                    .name(cinemaRoomName)
                    .rows(cinemaRoomRows)
                    .cinema(cinemaEntity)
                    .places(cinemaRoomPlaces)
                    .build());
        }

        return resultList;
    }
}