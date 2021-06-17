package model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.user.User;
import types.Role;

import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserDto {
    private String username;
    private Integer age;

    @Email(message = "Email is not correct")
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
                .build();
    }
}
