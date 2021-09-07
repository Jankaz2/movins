package kazmierczak.jan.events.listener;

import kazmierczak.jan.events.exception.EventsException;
import kazmierczak.jan.model.user.UserUtils;
import kazmierczak.jan.model.user.dto.ForgotPasswordDto;
import kazmierczak.jan.model.verification_token.repository.VerificationTokenRepository;
import kazmierczak.jan.persistence.dao.UserEntityDao;
import kazmierczak.jan.persistence.dao.VerificationTokenEntityDao;
import kazmierczak.jan.persistence.entity.UserEntity;
import kazmierczak.jan.persistence.entity.VerificationTokenEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;
import static java.lang.Thread.sleep;
import static java.time.LocalDateTime.now;
import static java.util.UUID.randomUUID;
import static kazmierczak.jan.model.user.UserUtils.*;

@Component
@RequiredArgsConstructor
public class ChangePasswordListener {
    private final UserEntityDao userEntityDao;
    private final JavaMailSender mailSender;
    private final VerificationTokenEntityDao verificationTokenEntityDao;
    private final VerificationTokenRepository repository;

    @Async
    @EventListener
    @Transactional
    public void sendChangePasswordEmail(ForgotPasswordDto forgotPasswordDto) {
        var userEmail = forgotPasswordDto.getEmail();

        var userToUpdatePassword = userEntityDao
                .findByEmail(userEmail)
                .orElseThrow(() -> new EventsException("Cannot find userToUpdatePassword with this email: " + userEmail));

        var token = randomUUID().toString().replaceAll("\\W", "");
        var user = userToUpdatePassword.toUser();
        var userId = toId.apply(user);

        repository.deleteByUserId(userId)
                .orElseThrow(() -> new EventsException("Cannot find token to delete with this user id: " + userId));

        var newVerificationToken = VerificationTokenEntity
                .builder()
                .token(token)
                .user(userToUpdatePassword)
                .dateTime(now().plusMinutes(5))
                .build();

        verificationTokenEntityDao.save(newVerificationToken);

        var username = userToUpdatePassword.getUsername();
        var recipientEmail = userToUpdatePassword.getEmail();
        var subject = "Change your password";

        var url = "http://localhost:5000/users/reset?token=" + token;
        var message = format("Hello, %s!" +
                "\nOpen this link to change your password!\n %s " +
                "\nMovins team", username, url);

        var simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(recipientEmail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        mailSender.send(simpleMailMessage);
    }
}
