package kazmierczak.jan.domain.model.user.dto;

import model.user.User;
import model.user.dto.CreateUserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import types.Role;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class CreateUserDtoTest {

    @Test
    @DisplayName("when method works correct")
    public void test1() {
        var user = User
                .builder()
                .name("name")
                .surname("surname")
                .age(18)
                .email("email@email.pl")
                .build();

        var userDto = CreateUserDto
                .builder()
                .name("name")
                .surname("surname")
                .age(18)
                .email("email@email.pl")
                .build();

        assertThat(userDto.toUser())
                .isEqualTo(user);
    }
}
