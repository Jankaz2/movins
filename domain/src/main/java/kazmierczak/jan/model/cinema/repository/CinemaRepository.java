package kazmierczak.jan.model.cinema.repository;

import kazmierczak.jan.config.repository.CrudRepository;
import kazmierczak.jan.model.address.Address;
import kazmierczak.jan.model.cinema.Cinema;

import java.util.Optional;

public interface CinemaRepository extends CrudRepository<Cinema, Long> {
    Optional<Cinema> findByName(String name);
    Optional<Cinema> findByAddress(Address address);
}
