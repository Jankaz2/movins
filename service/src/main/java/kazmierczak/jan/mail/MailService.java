package kazmierczak.jan.mail;

import kazmierczak.jan.mail.exception.MailServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;


@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String username;

    /**
     *
     * @param from email sender
     * @param subject subject of email
     * @param text content of email
     * @param isHtmlContent true if its html content, otherwise false
     */
    public void sendMail(String from, String subject, String text, boolean isHtmlContent) {
        try {
            var mimeMessage = javaMailSender.createMimeMessage();
            var mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(username);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(text, isHtmlContent);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException exception) {
            throw new MailServiceException(exception.getMessage());
        }
    }
}