package kazmierczak.jan.model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import kazmierczak.jan.types.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserResponseDto {
    private Long id;
    private String username;
    private String email;
    private Integer age;
    private String password;
    private Role role;
}
