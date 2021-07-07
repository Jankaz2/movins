package kazmierczak.jan.model.user.repository;

import kazmierczak.jan.config.repository.CrudRepository;
import kazmierczak.jan.model.user.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
