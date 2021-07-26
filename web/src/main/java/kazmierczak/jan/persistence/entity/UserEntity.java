package kazmierczak.jan.persistence.entity;

import kazmierczak.jan.persistence.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import kazmierczak.jan.model.user.User;
import kazmierczak.jan.types.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static kazmierczak.jan.model.user.UserUtils.*;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Getter
@Table(name = "cinema_users")
public class UserEntity extends BaseEntity {
    private String username;
    private String email;
    private Integer age;
    private String password;
    private boolean enabled;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<TicketEntity> tickets = new ArrayList<>();

    /**
     * @return User object mapped from UserEntity
     */
    public User toUser() {
        return User.builder()
                .id(id)
                .username(username)
                .age(age)
                .role(role)
                .password(password)
                .email(email)
                .tickets(new ArrayList<>())
                .enabled(enabled)
                .build();
    }

    /**
     * @param user object we want to map from
     * @return UserEntity object mapped from User object
     */
    public static UserEntity fromUserToEntity(User user) {
        if (user == null) {
            return null;
        }
        var userId = toId.apply(user);
        var userName = toUsername.apply(user);
        var userAge = toAge.apply(user);
        var userRole = toRole.apply(user);
        var userPassword = toPassword.apply(user);
        var userEmail = toEmail.apply(user);
        var userTickets = toTickets.apply(user);
        var enabled = toEnabled.apply(user);

        return UserEntity
                .builder()
                .id(userId)
                .username(userName)
                .age(userAge)
                .role(userRole)
                .password(userPassword)
                .email(userEmail)
                .tickets(new ArrayList<>())
                .enabled(enabled)
                .build();
    }

}
