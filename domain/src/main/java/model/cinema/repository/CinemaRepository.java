package model.cinema.repository;

import config.repository.CrudRepository;
import model.address.Address;
import model.cinema.Cinema;

import java.util.Optional;

public interface CinemaRepository extends CrudRepository<Cinema, Long> {
    Optional<Cinema> findByName(String name);
    Optional<Cinema> findByAddress(Address address);
}
