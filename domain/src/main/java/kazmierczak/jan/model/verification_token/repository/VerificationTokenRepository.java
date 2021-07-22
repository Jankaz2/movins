package kazmierczak.jan.model.verification_token.repository;

import kazmierczak.jan.model.verification_token.VerificationToken;

import java.util.Optional;

public interface VerificationTokenRepository {
    Optional<VerificationToken> findByToken(String token);
}
