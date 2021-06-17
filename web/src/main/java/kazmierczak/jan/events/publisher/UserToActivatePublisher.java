package kazmierczak.jan.events.publisher;

import kazmierczak.jan.user.EventPublisher;
import lombok.RequiredArgsConstructor;
import model.user.dto.UserToActivateDto;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserToActivatePublisher implements EventPublisher<UserToActivateDto> {
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publishEvent(UserToActivateDto userToActivateDto) {
        applicationEventPublisher.publishEvent(userToActivateDto);
    }
}
