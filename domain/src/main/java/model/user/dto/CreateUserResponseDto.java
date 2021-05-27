package model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.ticket.Ticket;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserResponseDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private Integer age;
    private String password;
}
