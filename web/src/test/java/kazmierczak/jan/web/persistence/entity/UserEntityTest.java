package kazmierczak.jan.web.persistence.entity;

import kazmierczak.jan.persistence.entity.UserEntity;
import model.ticket.Ticket;
import model.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class UserEntityTest {
    @Test
    @DisplayName("when mapping method works correctly")
    public void test1() {
        var user = User.builder()
                .id(1L)
                .name("name")
                .surname("surname")
                .age(12)
                .password("password")
                .email("email")
                .tickets(new ArrayList<>())
                .build();

        var entityUser = UserEntity
                .builder()
                .id(1L)
                .name("name")
                .surname("surname")
                .age(12)
                .password("password")
                .email("email")
                .tickets(new ArrayList<>())
                .build();

        assertThat(entityUser.toUser())
                .isEqualTo(user);
    }
}
