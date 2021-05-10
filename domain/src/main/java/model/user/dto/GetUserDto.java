package model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import types.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetUserDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private Integer age;
    private Role role;
    private String password;
}
