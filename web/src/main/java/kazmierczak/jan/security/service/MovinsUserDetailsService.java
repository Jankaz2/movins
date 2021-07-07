package kazmierczak.jan.security.service;

import kazmierczak.jan.security.exception.MovinsSecurityException;
import lombok.RequiredArgsConstructor;
import kazmierczak.jan.model.user.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovinsUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null) {
            throw new MovinsSecurityException("Username not found");
        }

        return userRepository
                .findByUsername(username)
                .map(user -> {
                    var authenticatedUser = user.toGetUserAuthentication();
                    return new User(
                            authenticatedUser.getUsername(),
                            authenticatedUser.getPassword(),
                            authenticatedUser.isEnabled(), true, true, true,
                            List.of(new SimpleGrantedAuthority(authenticatedUser.getRole().toString()))
                    );
                }).orElseThrow(() -> new MovinsSecurityException("Cannot authenticate user"));
    }
}
