package kazmierczak.jan.domain.model.user.dto;

import model.user.User;
import model.user.dto.CreateUserResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import types.Role;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class CreateUserResponseDtoTest {

    @Test
    @DisplayName("when method returns correct object")
    public void test1() {
        var user = User
                .builder()
                .id(1L)
                .username("name")
                .age(18)
                .email("email@email.pl")
                .password("password")
                .role(Role.USER)
                .build();

        var createUserResponseDto = CreateUserResponseDto
                .builder()
                .id(1L)
                .username("name")
                .age(18)
                .email("email@email.pl")
                .password("password")
                .build();

        assertThat(user.toCreateUserResponseDto())
                .isEqualTo(createUserResponseDto);
    }
}
