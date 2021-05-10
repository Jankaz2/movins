package model.user;

import lombok.*;
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
}
