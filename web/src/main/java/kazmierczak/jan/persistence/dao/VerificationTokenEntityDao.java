package kazmierczak.jan.persistence.dao;

import kazmierczak.jan.persistence.entity.VerificationTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationTokenEntityDao extends JpaRepository<VerificationTokenEntity, Long> {
    Optional<VerificationTokenEntity> findByToken(String token);
    Optional<VerificationTokenEntity> findByUser_Id(Long id);
    Optional<VerificationTokenEntity> deleteByUser_Id(Long id);
   // Optional<VerificationTokenEntity> deleteVerificationTokenEntity(VerificationTokenEntity verificationTokenEntity);
}
