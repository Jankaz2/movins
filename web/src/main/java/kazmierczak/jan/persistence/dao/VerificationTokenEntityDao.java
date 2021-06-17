package kazmierczak.jan.persistence.dao;

import kazmierczak.jan.persistence.entity.VerificationTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenEntityDao extends JpaRepository<VerificationTokenEntity, Long> {
}
