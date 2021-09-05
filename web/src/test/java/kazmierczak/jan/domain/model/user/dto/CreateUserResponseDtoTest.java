package kazmierczak.jan.domain.model.user.dto;

import kazmierczak.jan.model.user.User;
import kazmierczak.jan.model.user.dto.CreateUserResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import kazmierczak.jan.types.Role;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class CreateUserResponseDtoTest {

    @Test
    @DisplayName("when method returns correct object")
    public void test1() {
        var user = User
                .builder()
                .id(1L)
                .username("Username")
                .age(18)
                .email("email@email.pl")
                .password("password")
                .role(Role.ROLE_USER)
                .build();

        var createUserResponseDto = CreateUserResponseDto
                .builder()
                .id(1L)
                .username("Username")
                .build();

        assertThat(user.toCreateUserResponseDto())
                .isEqualTo(createUserResponseDto);
    }
}
