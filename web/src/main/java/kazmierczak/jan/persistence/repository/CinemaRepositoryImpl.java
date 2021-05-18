package kazmierczak.jan.persistence.repository;

import kazmierczak.jan.persistence.dao.CinemaEntityDao;
import kazmierczak.jan.persistence.entity.CinemaEntity;
import kazmierczak.jan.persistence.exception.PersistenceException;
import lombok.RequiredArgsConstructor;
import model.cinema.Cinema;
import model.cinema.repository.CinemaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static kazmierczak.jan.persistence.entity.CinemaEntity.*;

@Repository
@RequiredArgsConstructor
public class CinemaRepositoryImpl implements CinemaRepository {
    private final CinemaEntityDao cinemaEntityDao;

    /**
     * @param name of cinema we want to find
     * @return cinema with name from params
     */
    @Override
    public Optional<Cinema> findByName(String name) {
        return cinemaEntityDao
                .findByName(name)
                .map(CinemaEntity::toCinema);
    }

    /**
     * @param cinema we want to add
     * @return added cinema object
     */
    @Override
    public Optional<Cinema> add(Cinema cinema) {
        var cinemaEntity = fromCinemaToEntity(cinema);
        var insertedCinema = cinemaEntityDao.save(cinemaEntity);

        return Optional.ofNullable(insertedCinema.toCinema());
    }

    /**
     *
     * @param id of cinema we want to delete
     * @return optional object if exists
     */
    @Override
    public Optional<Cinema> delete(Long id) {
        var cinemaEntity = cinemaEntityDao
                .findById(id)
                .orElseThrow(() -> new PersistenceException("Cannot find cinema with this id"));

        cinemaEntityDao.delete(cinemaEntity);
        return Optional.ofNullable(cinemaEntity.toCinema());
    }

    /**
     *
     * @param id of cinema we want to find
     * @return cinema object
     */
    @Override
    public Optional<Cinema> findById(Long id) {
        return cinemaEntityDao
                .findById(id)
                .map(CinemaEntity::toCinema);
    }

    /**
     *
     * @return list of all cinemas
     */
    @Override
    public List<Cinema> findAll() {
        return cinemaEntityDao
                .findAll()
                .stream()
                .map(CinemaEntity::toCinema)
                .toList();
    }

    /**
     *
     * @param ids we want to cinemas with
     * @return all cinemas with id from ids list
     */
    @Override
    public List<Cinema> findAllById(List<Long> ids) {
        return cinemaEntityDao
                .findAllById(ids)
                .stream()
                .map(CinemaEntity::toCinema)
                .toList();
    }
}
