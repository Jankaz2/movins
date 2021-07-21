package kazmierczak.jan.persistence.repository;

import kazmierczak.jan.model.movie.Movie;
import kazmierczak.jan.model.movie.repository.MovieRepository;
import kazmierczak.jan.persistence.dao.MovieEntityDao;
import kazmierczak.jan.persistence.entity.MovieEntity;
import kazmierczak.jan.persistence.exception.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static kazmierczak.jan.persistence.entity.MovieEntity.*;

@Repository
@RequiredArgsConstructor
public class MovieRepositoryImpl implements MovieRepository {
    private final MovieEntityDao movieEntityDao;

    /**
     *
     * @param title we want to find movie by
     * @return found movie
     */
    @Override
    public Optional<Movie> findByTitle(String title) {
        return movieEntityDao
                .findByTitle(title)
                .map(MovieEntity::toMovie);
    }

    /**
     *
     * @param movie we want to add
     * @return optional of nullable of added movie
     */
    @Override
    public Optional<Movie> add(Movie movie) {
        var insertedMovie = fromMovieToEntity(movie);
        movieEntityDao.save(insertedMovie);

        return Optional.ofNullable(insertedMovie.toMovie());
    }

    /**
     *
     * @param id of movie we want to delete
     * @return optional of nullable of deleted movie
     */
    @Override
    public Optional<Movie> delete(Long id) {
        var movieToDelete = movieEntityDao.findById(id)
                .orElseThrow(() -> new PersistenceException("Cannot find movie with this id: " + id));
        movieEntityDao.delete(movieToDelete);
        return Optional.ofNullable(movieToDelete.toMovie());
    }

    /**
     *
     * @param id of movie we want to find
     * @return found movie
     */
    @Override
    public Optional<Movie> findById(Long id) {
        return movieEntityDao
                .findById(id)
                .map(MovieEntity::toMovie);
    }

    /**
     *
     * @return list of all movies
     */
    @Override
    public List<Movie> findAll() {
        return movieEntityDao
                .findAll()
                .stream()
                .map(MovieEntity::toMovie)
                .toList();
    }

    /**
     *
     * @param ids of movies we want to find
     * @return list of movies with these ids
     */
    @Override
    public List<Movie> findAllById(List<Long> ids) {
        return movieEntityDao
                .findAllById(ids)
                .stream()
                .map(MovieEntity::toMovie)
                .toList();
    }
}
