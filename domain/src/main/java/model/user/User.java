package model.user;

import lombok.*;
import model.ticket.Ticket;
import model.user.dto.CreateUserResponseDto;
import model.user.dto.GetUserDto;
import types.Role;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class User {
    Long id;
    String username;
    String email;
    Integer age;
    Role role;
    String password;
    List<Ticket> tickets;
    boolean enabled;

    /**
     * @return GetUserDto object
     */
    public GetUserDto toGetUserDto() {
        return GetUserDto
                .builder()
                .id(id)
                .username(username)
                .email(email)
                .age(age)
                .password(password)
                .role(role)
                .build();
    }

    /**
     * @return CreateUserResponseDto object
     */
    public CreateUserResponseDto toCreateUserResponseDto() {
        return CreateUserResponseDto
                .builder()
                .id(id)
                .username(username)
                .age(age)
                .email(email)
                .password(password)
                .role(role)
                .build();
    }
}
