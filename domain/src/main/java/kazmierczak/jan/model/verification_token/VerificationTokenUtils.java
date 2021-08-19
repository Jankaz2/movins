package kazmierczak.jan.model.verification_token;

import kazmierczak.jan.model.user.User;

import java.time.LocalDateTime;
import java.util.function.Function;

public interface VerificationTokenUtils {
    /**
     * Map verification token object to id of this token
     */
    Function<VerificationToken, Long> toId = verificationToken -> verificationToken.id;

    /**
     * Map verification token object to token of this object
     */
    Function<VerificationToken, String> toToken = verificationToken ->  verificationToken.token;

    /**
     * Map verification token object to date time of this token
     */
    Function<VerificationToken, LocalDateTime> toDateTime = verificationToken -> verificationToken.dateTime;

    /**
     * Map verification token object to user of this token
     */
    Function<VerificationToken, User> toUser = verificationToken -> verificationToken.user;
}
