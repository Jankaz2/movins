package model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.ticket.Ticket;
import model.user.User;
import types.Role;

import javax.validation.constraints.Email;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserDto {
    private String name;
    private String surname;
    private Integer age;

    @Email(message = "Email is not correct")
    private String email;
    private List<Ticket> tickets;

    /**
     * @return User object created from UserDto
     */
    public User toUser() {
        return User
                .builder()
                .name(name)
                .surname(surname)
                .age(age)
                .email(email)
                .tickets(tickets)
                .build();
    }
}
