package kazmierczak.jan.model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import kazmierczak.jan.types.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetUserAuthentication {
    private String username;
    private String password;
    private boolean enabled;
    private Role role;
}
