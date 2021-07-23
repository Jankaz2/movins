package kazmierczak.jan.model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import kazmierczak.jan.model.user.User;
import kazmierczak.jan.types.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserDto {
    private String username;
    private Integer age;
    private String email;
    private String password;
    private Role role;
    /**
     * @return User object created from UserDto
     */
    public User toUser() {
        return User
                .builder()
                .username(username)
                .age(age)
                .email(email)
                .password(password)
                .role(role)
                .enabled(false)
                .build();
    }
}
