package kazmierczak.jan.security.filters;

import kazmierczak.jan.security.tokens.MovinsTokensService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MovinsAuthorizationFilter extends BasicAuthenticationFilter {
    private final MovinsTokensService movinsTokensService;

    public MovinsAuthorizationFilter(AuthenticationManager authenticationManager, MovinsTokensService movinsTokensService) {
        super(authenticationManager);
        this.movinsTokensService = movinsTokensService;
    }

    /**
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doFilterInternal
            (HttpServletRequest request,
             HttpServletResponse response,
             FilterChain chain) throws IOException, ServletException {
        var header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null) {
            var authorizedUser = movinsTokensService.parseAccessToken(header);

            SecurityContextHolder.getContext().setAuthentication(authorizedUser);
        }

        chain.doFilter(request, response);
    }
}
