package kazmierczak.jan.persistence.dao;

import kazmierczak.jan.persistence.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieEntityDao extends JpaRepository<MovieEntity, Long> {
    Optional<MovieEntity> findByTitle(String title);
}
