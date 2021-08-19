package kazmierczak.jan.persistence.repository;

import kazmierczak.jan.model.verification_token.VerificationToken;
import kazmierczak.jan.model.verification_token.repository.VerificationTokenRepository;
import kazmierczak.jan.persistence.dao.VerificationTokenEntityDao;
import kazmierczak.jan.persistence.entity.VerificationTokenEntity;
import kazmierczak.jan.persistence.exception.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.*;
import static kazmierczak.jan.model.verification_token.VerificationTokenUtils.toId;

@Repository
@RequiredArgsConstructor
public class VerificationTokenRepositoryImpl implements VerificationTokenRepository {
    private final VerificationTokenEntityDao verificationTokenEntityDao;

    @Override
    public Optional<VerificationToken> add(VerificationToken verificationToken) {
        return empty();
    }

    @Override
    public Optional<VerificationToken> delete(Long id) {
        var tokenToDelete = verificationTokenEntityDao
                .findById(id)
                .orElseThrow(() -> new PersistenceException("Cannot find verification token with this id: " + id));

        verificationTokenEntityDao
                .delete(tokenToDelete);

        return ofNullable(tokenToDelete.toVerificationToken());
    }

    @Override
    public Optional<VerificationToken> findById(Long aLong) {
        return empty();
    }

    @Override
    public List<VerificationToken> findAll() {
        return null;
    }

    @Override
    public List<VerificationToken> findAllById(List<Long> longs) {
        return null;
    }

    /**
     * @param token we want to find by
     * @return verification token optional object
     */
    @Override
    public Optional<VerificationToken> findByToken(String token) {
        return verificationTokenEntityDao
                .findByToken(token)
                .map(VerificationTokenEntity::toVerificationToken);
    }

    /**
     * @param id of user we want to find token by
     * @return found token
     */
    @Override
    public Optional<VerificationToken> findByUserId(Long id) {
        return verificationTokenEntityDao
                .findByUser_Id(id)
                .map(VerificationTokenEntity::toVerificationToken);
    }

    /**
     * @param id of user we want to find token by
     * @return found token
     */
    @Override
    public Optional<VerificationToken> deleteByUserId(Long id) {
        var token = findByUserId(id)
                .orElseThrow(() -> new PersistenceException("Cannot find token with this user id: " + id));

        var idOfTokenWeWantToDelete = toId.apply(token);
        var deletedToken = delete(idOfTokenWeWantToDelete)
                .orElseThrow(() -> new PersistenceException("Cannot delete token"));

        return of(deletedToken);
    }

}
