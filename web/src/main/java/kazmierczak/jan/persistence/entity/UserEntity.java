package kazmierczak.jan.persistence.entity;

import kazmierczak.jan.persistence.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import model.user.User;
import types.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static model.user.UserUtils.*;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Getter
@Table(name = "cinema_users")
public class UserEntity extends BaseEntity {
    private String name;
    private String surname;
    private String email;
    private Integer age;

    @Enumerated(EnumType.STRING)
    private Role role;
    private String password;

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<TicketEntity> tickets = new ArrayList<>();

    /**
     * @return User object mapped from UserEntity
     */
    public User toUser() {
        return User.builder()
                .id(id)
                .username(name)
                .surname(surname)
                .age(age)
                .role(role)
                .password(password)
                .email(email)
                .tickets(new ArrayList<>())
                .build();
    }

    /**
     *
     * @param user object we want to map from
     * @return UserEntity object mapped from User object
     */
    public static UserEntity fromUserToEntity(User user) {
        var userId = toId.apply(user);
        var userName = toName.apply(user);
        var userSurname = toSurname.apply(user);
        var userAge = toAge.apply(user);
        var userRole = toRole.apply(user);
        var userPassword = toPassword.apply(user);
        var userEmail = toEmail.apply(user);
        var userTickets = toTickets.apply(user);

        return UserEntity
                .builder()
                .id(userId)
                .name(userName)
                .surname(userSurname)
                .age(userAge)
                .role(userRole)
                .password(userPassword)
                .email(userEmail)
                .tickets(new ArrayList<>())
                .build();
    }

}
