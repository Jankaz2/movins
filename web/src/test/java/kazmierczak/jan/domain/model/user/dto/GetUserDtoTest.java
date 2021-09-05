package kazmierczak.jan.domain.model.user.dto;

import kazmierczak.jan.model.user.User;
import kazmierczak.jan.model.user.dto.GetUserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import kazmierczak.jan.types.Role;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class GetUserDtoTest {
    @Test
    @DisplayName("when method works correct")
    public void test1() {
        var user = User
                .builder()
                .id(1L)
                .username("name")
                .age(18)
                .email("email@email.pl")
                .role(Role.ROLE_USER)
                .build();

        var userDto = GetUserDto
                .builder()
                .id(1L)
                .username("name")
                .age(18)
                .email("email@email.pl")
                .role(Role.ROLE_USER)
                .build();

        assertThat(user.toGetUserDto())
                .isEqualTo(userDto);
    }
}
