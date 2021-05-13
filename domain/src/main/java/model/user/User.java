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
    String name;
    String surname;
    String email;
    Integer age;
    Role role;
    String password;
    List<Ticket> tickets;

    /**
     * @return GetUserDto object
     */
    public GetUserDto toGetUserDto() {
        return GetUserDto
                .builder()
                .id(id)
                .name(name)
                .surname(surname)
                .email(email)
                .age(age)
                .password(password)
                .tickets(tickets)
                .build();
    }

    /**
     * @return CreateUserResponseDto object
     */
    public CreateUserResponseDto toCreateUserResponseDto() {
        return CreateUserResponseDto
                .builder()
                .id(id)
                .name(name)
                .surname(surname)
                .age(age)
                .email(email)
                .password(password)
                .tickets(tickets)
                .build();
    }
}
