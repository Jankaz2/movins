package kazmierczak.jan.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import kazmierczak.jan.security.dto.AuthenticationDto;
import kazmierczak.jan.security.exception.MovinsSecurityException;
import kazmierczak.jan.security.tokens.MovinsTokensService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Collections.emptyList;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class MovinsAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final MovinsTokensService movinsTokensService;

    public MovinsAuthenticationFilter(AuthenticationManager authenticationManager, MovinsTokensService movinsTokensService) {
        this.authenticationManager = authenticationManager;
        this.movinsTokensService = movinsTokensService;
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException {
        try {
            var userToAuthenticate
                    = new ObjectMapper().readValue(request.getInputStream(), AuthenticationDto.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userToAuthenticate.getUsername(),
                    userToAuthenticate.getPassword(),
                    emptyList()
            ));
        } catch (Exception e) {
            throw new MovinsSecurityException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult) throws IOException {

        var tokens = movinsTokensService.createTokens(authResult);
        response.setContentType(APPLICATION_JSON_VALUE);
        response.getWriter().write(new ObjectMapper().writeValueAsString(tokens));
        response.getWriter().flush();
        response.getWriter().close();
    }
}