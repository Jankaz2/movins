package model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.user.User;
import types.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserDto {
    private String name;
    private String surname;
    private Integer age;
    private String email;
    private Role role;

    /**
     *
     * @return User object created from UserDto
     */
    public User toUser() {
        return User
                .builder()
                .name(name)
                .surname(surname)
                .age(age)
                .email(email)
                .role(role)
                .build();
    }
}
