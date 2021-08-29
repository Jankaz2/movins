package kazmierczak.jan.events.listener;

import kazmierczak.jan.events.exception.EventsException;
import kazmierczak.jan.model.user.dto.UserToActivateDto;
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
public class UserToActivateListener {
    private final UserEntityDao userEntityDao;
    private final JavaMailSender mailSender;
    private final VerificationTokenEntityDao verificationTokenEntityDao;

    @Async
    @EventListener
    @Transactional
    public void sendActivationEmail(UserToActivateDto userToActivateDto) {
        var userId = userToActivateDto.getId();

        try { sleep(500); } catch (Exception ignored) { }

        var userToActivate = userEntityDao
                .findById(userId)
                .orElseThrow(() -> new EventsException("Cannot find userToActivateDto with this id: " + userId));

        var token = randomUUID().toString().replaceAll("\\W", "");

        var verificationToken = VerificationTokenEntity
                .builder()
                .token(token)
                .user(userToActivate)
                .dateTime(now().plusMinutes(5))
                .build();

        verificationTokenEntityDao.save(verificationToken);

        var username = userToActivate.getUsername();
        var recipientEmail = userToActivate.getEmail();
        var subject = "Activate your account";

        var url = "http://localhost:5000/users/activation?token=" + token;
        var message = format("Hello, %s! " +
                "\nWe are so happy that u decided to start working with Movins!" +
                "\nLast step is to click this link and finish your registration!\n %s " +
                "\n\nWish you the best, %s" +
                "\nMovins team", username, url, username);

        var simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(recipientEmail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        mailSender.send(simpleMailMessage);
    }
}
