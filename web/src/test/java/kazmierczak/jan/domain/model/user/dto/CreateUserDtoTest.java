package kazmierczak.jan.domain.model.user.dto;

import kazmierczak.jan.model.user.User;
import kazmierczak.jan.model.user.dto.CreateUserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class CreateUserDtoTest {

    @Test
    @DisplayName("when method works correct")
    public void test1() {
        var user = User
                .builder()
                .username("name")
                .age(18)
                .email("email@email.pl")
                .build();

        var userDto = CreateUserDto
                .builder()
                .username("name")
                .age(18)
                .email("email@email.pl")
                .build();

        assertThat(userDto.toUser())
                .isEqualTo(user);
    }
}
