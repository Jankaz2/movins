package kazmierczak.jan.events.listener;

import kazmierczak.jan.events.exception.EventsException;
import kazmierczak.jan.persistence.dao.UserEntityDao;
import kazmierczak.jan.persistence.dao.VerificationTokenEntityDao;
import kazmierczak.jan.persistence.entity.VerificationTokenEntity;
import lombok.RequiredArgsConstructor;
import model.user.dto.UserToActivateDto;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserToActivateListener {
    private final UserEntityDao userEntityDao;
    private final JavaMailSender mailSender;
    private final VerificationTokenEntityDao verificationTokenEntityDao;

    @Async
    @EventListener
    public void sendActivationEmail(UserToActivateDto userToActivateDto) {
        var userId = userToActivateDto.getId();
        var userToActivate = userEntityDao
                .findById(userId)
                .orElseThrow(() -> new EventsException("Cannot find userToActivateDto with this id: " + userId));

        var token = UUID.randomUUID().toString().replaceAll("\\W", "");

        var verificationToken = VerificationTokenEntity
                .builder()
                .token(token)
                .user(userToActivate)
                .dateTime(LocalDateTime.now().plusMinutes(5))
                .build();

        var insertedVerificationToken = verificationTokenEntityDao.save(verificationToken);

        var recipientEmail =  userToActivate.getEmail();
        var subject = "Registration activate";
        var url = "http://localhost:3000/user/activate?token=" + token;

        var simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(recipientEmail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(url);

        mailSender.send(simpleMailMessage);
    }
}
