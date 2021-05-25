package kazmierczak.jan.controller;

import kazmierczak.jan.controller.dto.ResponseDto;
import kazmierczak.jan.mail.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cinema")
public class MailController {
    private final MailService mailService;

    /**
     *
     * @param from email sender
     * @param subject subject of email
     * @param text content of email
     * @return confirmation message
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/send-mail")
    public String sendMail(
            @RequestParam("from") String from,
            @RequestParam("subject") String subject,
            @RequestParam("text") String text) {
        mailService.sendMail(from, subject, text, true);
        return "";
    }
}
