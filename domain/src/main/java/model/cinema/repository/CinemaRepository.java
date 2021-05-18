package model.cinema.repository;

import config.repository.CrudRepository;
import model.cinema.Cinema;

import java.util.Optional;

public interface CinemaRepository extends CrudRepository<Cinema, Long> {
    Optional<Cinema> findByName(String name);
}
