package kazmierczak.jan.model.verification_token;

import kazmierczak.jan.model.user.User;
import lombok.*;

import java.time.LocalDateTime;

import static kazmierczak.jan.model.user.UserUtils.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class VerificationToken {
    Long id;
    String token;
    LocalDateTime dateTime;
    User user;

    public boolean isValid() {
        return LocalDateTime.now().isBefore(dateTime);
    }

    public Long getUserId() {
        return toId.apply(user);
    }

    public VerificationToken withChangedToken(String newToken) {
        return VerificationToken
                .builder()
                .id(id)
                .token(newToken)
                .dateTime(dateTime)
                .user(user)
                .build();
    }
}
