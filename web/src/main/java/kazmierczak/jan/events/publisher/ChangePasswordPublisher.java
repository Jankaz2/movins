package kazmierczak.jan.events.publisher;

import kazmierczak.jan.model.user.dto.ForgotPasswordDto;
import kazmierczak.jan.user.EventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChangePasswordPublisher implements EventPublisher<ForgotPasswordDto> {
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publishEvent(ForgotPasswordDto forgotPasswordDto) {
        applicationEventPublisher.publishEvent(forgotPasswordDto);
    }
}
