package kazmierczak.jan.security.tokens;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import kazmierczak.jan.config.MovinsAppConfig;
import kazmierczak.jan.security.tokens.dto.TokensDto;
import kazmierczak.jan.security.tokens.exception.AppTokensException;
import lombok.RequiredArgsConstructor;
import model.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.util.Date;

import static model.user.UserUtils.*;

@Service
@RequiredArgsConstructor
public class AppTokensService {
    private final UserRepository userRepository;
    private SecretKey secretKey;

    @Value("${tokens.access-token.expiration-time-ms}")
    private Long accessTokenExpirationTimeMs;

    @Value("${tokens.refresh-token.expiration-time-ms}")
    private Long refreshTokenExpirationTimeMs;

    @Value("${tokens.refresh-token.access-token-expiration-time-ms-property}")
    private String refreshTokenAccessTokenExpirationTimeMsProperty;

    @Value("${tokens.prefix}")
    private String tokensPrefix;

    /**
     *
      * @param authentication
     * @return
     */
    public TokensDto createTokens(Authentication authentication) {
        var context = new AnnotationConfigApplicationContext(MovinsAppConfig.class);
        secretKey = (SecretKey) context.getBean("secretKey");

        var username = authentication.getName();
        var userId = userRepository
                .findByUsername(username)
                .map(toId)
                .orElseThrow(() -> new AppTokensException("Cannot find User with this username: " + username));

        var currentDate = new Date();
        var accessTokenExpirationDate = new Date(currentDate.getTime() + accessTokenExpirationTimeMs);
        var refreshTokenExpirationDate = new Date(currentDate.getTime() + refreshTokenExpirationTimeMs);

        var accessToken = Jwts
                .builder()
                .setSubject(String.valueOf(userId))
                .setExpiration(accessTokenExpirationDate)
                .setIssuedAt(currentDate)
                .signWith(secretKey)
                .compact();

        var refreshToken = Jwts
                .builder()
                .setSubject(String.valueOf(userId))
                .setExpiration(refreshTokenExpirationDate)
                .setIssuedAt(currentDate)
                .claim(refreshTokenAccessTokenExpirationTimeMsProperty, accessTokenExpirationDate.getTime())
                .signWith(secretKey)
                .compact();

        return TokensDto
                .builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private Claims claims(String token) {
        var context = new AnnotationConfigApplicationContext(MovinsAppConfig.class);
        secretKey = (SecretKey) context.getBean("secretKey");

        return Jwts
                .parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Long id(String token) {
        return Long.parseLong(claims(token).getSubject());
    }

    private Date expirationDate(String token) {
        return claims(token).getExpiration();
    }

    private boolean isTokenNotValid(String token) {
        return expirationDate(token).before(new Date());
    }
}