package kazmierczak.jan.persistence.entity;

import kazmierczak.jan.persistence.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import types.Role;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "cinema_users")
public class UserEntity extends BaseEntity {
    private String name;
    private String surname;
    private String email;
    private Integer age;
    private Role role;
    private String password;

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<TicketEntity> tickets = new ArrayList<>();
}