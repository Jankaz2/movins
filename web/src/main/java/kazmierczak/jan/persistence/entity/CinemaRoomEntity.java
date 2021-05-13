package kazmierczak.jan.persistence.entity;

import kazmierczak.jan.persistence.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import model.cinemas.CinemaRoom;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "cinema_rooms")
public class CinemaRoomEntity extends BaseEntity {
    private String name;
    private Integer cinema_rows;
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
                .rows(cinema_rows)
                .places(places)
                .seats(new ArrayList<>())
                .build();
    }
}
