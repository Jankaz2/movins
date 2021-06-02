package kazmierczak.jan.persistence.entity;

import kazmierczak.jan.persistence.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import model.seance.Seance;
import model.seance.SeanceUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static kazmierczak.jan.persistence.entity.CinemaRoomEntity.*;
import static kazmierczak.jan.persistence.entity.MovieEntity.*;
import static kazmierczak.jan.persistence.entity.TicketEntity.*;
import static model.seance.SeanceUtils.*;

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

    private LocalDate date;

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
                .date(date)
                .build();
    }

    /**
     *
     * @param seances list we want to map
     * @return list of seance entity objects
     */
    public static List<SeanceEntity> fromSeancesToEntityList(List<Seance> seances) {
        return seances
                .stream()
                .map(SeanceEntity::fromSeanceToEntity)
                .toList();
    }

    /**
     *
     * @param seance object we want to map
     * @return seance entity object
     */
    public static SeanceEntity fromSeanceToEntity(Seance seance) {
        var seanceId = toSeanceId.apply(seance);
        var seanceMovie = toSeanceMovie.apply(seance);
        var seanceCinemaRoom = toSeanceCinemaRooom.apply(seance);
        var seanceTickets = toSeanceTickets.apply(seance);
        var seanceDate = toSeanceDate.apply(seance);

        return SeanceEntity
                .builder()
                .id(seanceId)
                .movie(fromMovieToEntity(seanceMovie))
               // .cinemaRoom(fromCinemaRooomtoEntity(seanceCinemaRoom))
                .tickets(fromTicketsToEntityList(seanceTickets))
                .date(seanceDate)
                .build();
    }
}
