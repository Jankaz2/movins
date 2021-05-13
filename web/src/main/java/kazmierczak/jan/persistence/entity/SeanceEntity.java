package kazmierczak.jan.persistence.entity;

import kazmierczak.jan.persistence.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import model.seance.Seance;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "seances")
public class SeanceEntity extends BaseEntity {
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "movie_id")
    private MovieEntity movie;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "cinema_room_id")
    private CinemaRoomEntity cinemaRoom;

    @OneToMany(mappedBy = "seance")
    @Builder.Default
    private List<TicketEntity> tickets = new ArrayList<>();

    private LocalDate localDate;

    /**
     *
     * @return Seance object mapped from SeanceEntity
     */
    public Seance toSeance() {
        return Seance.builder()
                .id(id)
                .movie(movie.toMovie())
                .cinemaRoom(cinemaRoom.toCinemaRoom())
                .tickets(new ArrayList<>())
                .build();
    }
}
