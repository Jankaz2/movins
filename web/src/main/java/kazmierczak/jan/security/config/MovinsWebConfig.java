package kazmierczak.jan.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import kazmierczak.jan.security.dto.ErrorDto;
import kazmierczak.jan.security.filters.MovinsAuthenticationFilter;
import kazmierczak.jan.security.filters.MovinsAuthorizationFilter;
import kazmierczak.jan.security.service.MovinsUserDetailsService;
import kazmierczak.jan.security.tokens.MovinsTokensService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static java.util.List.of;
import static org.springframework.http.MediaType.*;
import static org.springframework.security.config.http.SessionCreationPolicy.*;

@EnableWebSecurity
@RequiredArgsConstructor
public class MovinsWebConfig extends WebSecurityConfigurerAdapter {
    private final MovinsUserDetailsService movinsUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final MovinsTokensService movinsTokensService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(movinsUserDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()

                .and()
                .csrf().disable()

                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint())
                .accessDeniedHandler(accessDeniedHandler())

                .and()
                .sessionManagement()
                .sessionCreationPolicy(STATELESS)

                .and()
                .authorizeRequests()

                .antMatchers(
                        "/login",
                        "/users/register",
                        "/users/reset",
                        "/users/activation",
                        "/users/forgot-password",
                        "/cinema",
                        "/cinema/{id}",
                        "/cinema/rooms",
                        "/cinema/seats",
                        "/cinema/movies",
                        "/cinema/rooms",
                        "/cinema/rooms/{id}",
                        "/cinema/rooms/name/{id}",
                        "/cinema/movies/seances/{id}",
                        "/security/refresh-tokens"
                ).permitAll()

                .antMatchers(
                        "/cinema/admin",
                        "/cinema/admin/{id}",
                        "/cinema/admin/{name}",
                        "/cinema/admin/{oldName}",
                        "/cinema/movies/admin/{id}",
                        "*/admin/*"
                ).hasRole("ADMIN")

                .antMatchers(
                        "/tickets",
                        "/tickets/{id}",
                        "/users/purchase/{id}",
                        "/users/{id}"
                ).hasAnyRole("ADMIN", "USER")

                .and()
                .addFilter(new MovinsAuthenticationFilter(authenticationManager(), movinsTokensService))
                .addFilter(new MovinsAuthorizationFilter(authenticationManager(), movinsTokensService));
    }

    private AuthenticationEntryPoint authenticationEntryPoint() {
        return (httpServletRequest, httpServletResponse, e) -> {
            var error = ErrorDto.builder().message(e.getMessage()).build();
            httpServletResponse.setContentType(APPLICATION_JSON_VALUE);
            httpServletResponse.getWriter().write(new ObjectMapper().writeValueAsString(error));
            httpServletResponse.getWriter().flush();
            httpServletResponse.getWriter().close();
        };
    }

    private AccessDeniedHandler accessDeniedHandler() {
        return (httpServletRequest, httpServletResponse, e) -> {
            var error = ErrorDto.builder().message(e.getMessage()).build();
            httpServletResponse.setContentType(APPLICATION_JSON_VALUE);
            httpServletResponse.getWriter().write(new ObjectMapper().writeValueAsString(error));
            httpServletResponse.getWriter().flush();
            httpServletResponse.getWriter().close();
        };
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(of("http://localhost:3000"));
        corsConfiguration.setAllowedMethods(of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedHeaders(of("Authorization", "Cache-Control", "Content-Type"));

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
