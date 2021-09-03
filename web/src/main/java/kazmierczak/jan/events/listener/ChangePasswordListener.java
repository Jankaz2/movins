package kazmierczak.jan.events.listener;

import kazmierczak.jan.events.exception.EventsException;
import kazmierczak.jan.model.user.dto.ForgotPasswordDto;
import kazmierczak.jan.persistence.dao.UserEntityDao;
import kazmierczak.jan.persistence.dao.VerificationTokenEntityDao;
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

@Component
@RequiredArgsConstructor
public class ChangePasswordListener {
    private final UserEntityDao userEntityDao;
    private final JavaMailSender mailSender;
    private final VerificationTokenEntityDao verificationTokenEntityDao;

    @Async
    @EventListener
    @Transactional
    public void sendChangePasswordEmail(ForgotPasswordDto forgotPasswordDto) {
        var userEmail = forgotPasswordDto.getEmail();

        var userToUpdatePassword = userEntityDao
                .findByEmail(userEmail)
                .orElseThrow(() -> new EventsException("Cannot find userToUpdatePassword with this email: " + userEmail));

        var token = randomUUID().toString().replaceAll("\\W", "");

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
