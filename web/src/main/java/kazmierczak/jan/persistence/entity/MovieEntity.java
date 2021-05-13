package kazmierczak.jan.persistence.entity;

import kazmierczak.jan.persistence.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import model.movie.Movie;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "movies")
public class MovieEntity extends BaseEntity {
    private String title;
    private String genre;
    private Integer duration;
    private LocalDate releaseDate;

    @OneToMany(mappedBy = "movie")
    @Builder.Default
    private List<SeanceEntity> seances = new ArrayList<>();

    /**
     *
     * @return Movie object mapped from MovieEntity
     */
    public Movie toMovie() {
        return Movie.builder()
                .id(id)
                .title(title)
                .genre(genre)
                .duration(duration)
                .releaseDate(releaseDate)
                .build();
    }
}
