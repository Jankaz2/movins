package kazmierczak.jan.domain.model.user.dto;

import model.user.User;
import model.user.dto.GetUserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import types.Role;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class GetUserDtoTest {
    @Test
    @DisplayName("when method works correct")
    public void test1() {
        var user = User
                .builder()
                .id(1L)
                .name("name")
                .surname("surname")
                .age(18)
                .email("email@email.pl")
                .role(Role.USER)
                .password("password")
                .build();

        var userDto = GetUserDto
                .builder()
                .id(1L)
                .name("name")
                .surname("surname")
                .age(18)
                .email("email@email.pl")
                .role(Role.USER)
                .password("password")
                .build();

        assertThat(user.toGetUserDto())
                .isEqualTo(userDto);
    }
}
