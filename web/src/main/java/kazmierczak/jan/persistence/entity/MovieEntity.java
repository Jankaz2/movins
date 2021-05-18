package kazmierczak.jan.persistence.entity;

import kazmierczak.jan.persistence.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import model.movie.Movie;
import model.movie.MovieUtils;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static kazmierczak.jan.persistence.entity.SeanceEntity.*;
import static model.movie.MovieUtils.*;

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
                .seances(new ArrayList<>())
                .build();
    }

    /**
     *
     * @param movie we want to map
     * @return movie entity object
     */
    public static MovieEntity fromMovieToEntity(Movie movie) {
        var movieId = toMovieId.apply(movie);
        var movieTitle = toMovieTitle.apply(movie);
        var movieGenre = toMovieGenre.apply(movie);
        var movieDuration = toMovieDuration.apply(movie);
        var movieReleaseDate = toMovieReleaseDate.apply(movie);
        var movieReleaseSeances = toMovieSeances.apply(movie);

        return MovieEntity
                .builder()
                .id(movieId)
                .title(movieTitle)
                .genre(movieGenre)
                .duration(movieDuration)
                .releaseDate(movieReleaseDate)
                .seances(fromSeancesToEntityList(movieReleaseSeances))
                .build();
    }
}
