package kazmierczak.jan.model.verification_token.repository;

import kazmierczak.jan.config.repository.CrudRepository;
import kazmierczak.jan.model.verification_token.VerificationToken;

import java.util.Optional;

public interface VerificationTokenRepository extends CrudRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);
    Optional<VerificationToken> findByUserId(Long id);
    Optional<VerificationToken> deleteByUserId(Long id);
   // Optional<VerificationToken> deleteVerificationTokenEntity(VerificationToken verificationToken);
}
