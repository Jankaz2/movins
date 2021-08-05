package kazmierczak.jan.persistence.entity;

import kazmierczak.jan.model.seance.Seance;
import kazmierczak.jan.persistence.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static kazmierczak.jan.model.seance.SeanceUtils.*;
import static kazmierczak.jan.persistence.entity.CinemaRoomEntity.fromCinemaRooomtoEntity;
import static kazmierczak.jan.persistence.entity.MovieEntity.fromMovieToEntity;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "seances")
public class SeanceEntity extends BaseEntity {
    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "movie_id")
    private MovieEntity movie;

    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "cinema_room_id")
    private CinemaRoomEntity cinemaRoom;

    @OneToMany(mappedBy = "seance")
    @Builder.Default
    private List<TicketEntity> tickets = new ArrayList<>();

    private LocalDate date;

    /**
     * @return Seance object mapped from SeanceEntity
     */
    public Seance toSeance() {
        return Seance.builder()
                .id(id)
                .movie(movie.toMovie())
                .cinemaRoom(cinemaRoom == null ? null : cinemaRoom.toCinemaRoom())
                .tickets(new ArrayList<>())
                .date(date)
                .build();
    }

    /**
     * @return Seance light object mapped from SeanceEntity
     */
    public Seance toSeanceLight() {
        return Seance
                .builder()
                .id(id)
                .cinemaRoom(cinemaRoom.toCinemaRoom())
                .date(date)
                .build();
    }

    /**
     * @param seances list we want to map
     * @return list of seance entity objects
     */
    public static List<SeanceEntity> fromSeancesToEntityList(List<Seance> seances) {
        var movieEntity = fromMovieToEntity(
                toSeanceMovie
                        .apply(seances
                                .stream()
                                .findFirst()
                                .orElseThrow()
                        )
        );

        var resultList = new ArrayList<SeanceEntity>();
        for (var seance : seances) {
            var seanceId = toSeanceId.apply(seance);
            var seanceCinemaRoom = toSeanceCinemaRooom.apply(seance);
            var seanceDate = toSeanceDate.apply(seance);

            resultList.add(SeanceEntity
                    .builder()
                    .id(seanceId)
                    .movie(movieEntity)
                    .cinemaRoom(fromCinemaRooomtoEntity(seanceCinemaRoom))
                    .date(seanceDate)
                    .build());
        }
        return resultList;
    }

    /**
     * @param seance object we want to map
     * @return seance entity object
     */
    public static SeanceEntity fromSeanceToEntity(Seance seance) {
        if (seance == null) {
            return null;
        }
        var seanceId = toSeanceId.apply(seance);
        var seanceMovie = toSeanceMovie.apply(seance);
        var seanceCinemaRoom = toSeanceCinemaRooom.apply(seance);
        var seanceDate = toSeanceDate.apply(seance);

        return SeanceEntity
                .builder()
                .id(seanceId)
                .movie(fromMovieToEntity(seanceMovie))
                .cinemaRoom(fromCinemaRooomtoEntity(seanceCinemaRoom))
                .date(seanceDate)
                .build();
    }
}
