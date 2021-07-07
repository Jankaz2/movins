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
public class GetUserAuthorization {
    private String username;
    private Role role;
}
