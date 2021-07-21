package kazmierczak.jan.model.movie.repository;

import kazmierczak.jan.config.repository.CrudRepository;
import kazmierczak.jan.model.movie.Movie;

import java.util.Optional;

public interface MovieRepository extends CrudRepository<Movie, Long> {
    Optional<Movie> findByTitle(String title);
}
