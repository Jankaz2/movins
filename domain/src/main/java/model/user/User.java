package model.user;

import lombok.*;
import model.user.dto.GetUserDto;
import types.Role;

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

    /**
     *
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
                .role(role)
                .password(password)
                .build();
    }
}
