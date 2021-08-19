package kazmierczak.jan.persistence.entity;

import kazmierczak.jan.model.verification_token.VerificationToken;
import kazmierczak.jan.persistence.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "verification_tokens")
public class VerificationTokenEntity extends BaseEntity {
    private String token;
    private LocalDateTime dateTime;

    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private UserEntity user;

    /**
     *
     * @return VerificationToken object mapped from entity
     */
    public VerificationToken toVerificationToken() {
        return VerificationToken
                .builder()
                .id(id)
                .token(token)
                .dateTime(dateTime)
                .user(user.toUser())
                .build();
    }
}
