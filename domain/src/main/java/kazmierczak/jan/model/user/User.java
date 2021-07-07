package kazmierczak.jan.model.user;

import lombok.*;
import kazmierczak.jan.model.ticket.Ticket;
import kazmierczak.jan.model.user.dto.CreateUserResponseDto;
import kazmierczak.jan.model.user.dto.GetUserAuthentication;
import kazmierczak.jan.model.user.dto.GetUserAuthorization;
import kazmierczak.jan.model.user.dto.GetUserDto;
import kazmierczak.jan.types.Role;

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

    /**
     *
     * @return getUserAuthentication object
     */
    public GetUserAuthentication toGetUserAuthentication() {
        return GetUserAuthentication
                .builder()
                .username(username)
                .password(password)
                .enabled(enabled)
                .role(role)
                .build();
    }

    /**
     *
     * @return getUserAuthorization object
     */
    public GetUserAuthorization toGetUserAuthorization() {
        return GetUserAuthorization
                .builder()
                .username(username)
                .role(role)
                .build();
    }
}
