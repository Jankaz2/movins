package model.movie;

import model.seance.Seance;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

public interface MovieUtils {
    /**
     * map movie to id of this movie
     */
    Function<Movie, Long> toMovieId = movie -> movie.id;

    /**
     * map movie to title of this movie
     */
    Function<Movie, String> toMovieTitle = movie -> movie.title;

    /**
     * map movie to genre of this movie
     */
    Function<Movie, String> toMovieGenre = movie -> movie.genre;

    /**
     * map movie to duration of this movie
     */
    Function<Movie, Integer> toMovieDuration = movie -> movie.duration;

    /**
     * map movie to release date of this movie
     */
    Function<Movie, LocalDate> toMovieReleaseDate = movie -> movie.releaseDate;

    /**
     * map movie to seances of this movie
     */
    Function<Movie, List<Seance>> toMovieSeances = movie -> movie.seances;
}
