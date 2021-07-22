package kazmierczak.jan.persistence.repository;

import kazmierczak.jan.model.verification_token.VerificationToken;
import kazmierczak.jan.model.verification_token.repository.VerificationTokenRepository;
import kazmierczak.jan.persistence.dao.VerificationTokenEntityDao;
import kazmierczak.jan.persistence.entity.VerificationTokenEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class VerificationTokenRepositoryImpl implements VerificationTokenRepository {
    private final VerificationTokenEntityDao verificationTokenEntityDao;

    /**
     *
     * @param token we want to find by
     * @return verification token optional object
     */
    @Override
    public Optional<VerificationToken> findByToken(String token) {
        return verificationTokenEntityDao
                .findByToken(token)
                .map(VerificationTokenEntity::toVerificationToken);
    }
}
