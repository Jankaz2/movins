package kazmierczak.jan.security.tokens;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import kazmierczak.jan.model.user.repository.UserRepository;
import kazmierczak.jan.security.dto.RefreshTokenDto;
import kazmierczak.jan.security.tokens.dto.TokensDto;
import kazmierczak.jan.security.tokens.exception.AppTokensException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

import static java.lang.String.*;
import static java.lang.System.*;
import static java.util.List.of;
import static kazmierczak.jan.model.user.UserUtils.toId;

@Service
@RequiredArgsConstructor
public class MovinsTokensService {

    @Value("${tokens.access-token.expiration-time-ms}")
    private Long accessTokenExpirationTimeMs;

    @Value("${tokens.refresh-token.expiration-time-ms}")
    private Long refreshTokenExpirationTimeMs;

    @Value("${tokens.refresh-token.access-token-expiration-time-ms-property}")
    private String refreshTokenProperty;

    @Value("${tokens.prefix}")
    private String tokensPrefix;

    private final UserRepository userRepository;
    private final SecretKey secretKey;

    /**
     * @param authentication
     * @return generated token dto
     */
    public TokensDto createTokens(Authentication authentication) {
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
                .setSubject(valueOf(userId))
                .setExpiration(accessTokenExpirationDate)
                .setIssuedAt(currentDate)
                .signWith(secretKey)
                .compact();

        var refreshToken = Jwts
                .builder()
                .setSubject(valueOf(userId))
                .setExpiration(refreshTokenExpirationDate)
                .setIssuedAt(currentDate)
                .claim(refreshTokenProperty, accessTokenExpirationDate.getTime())
                .signWith(secretKey)
                .compact();

        return TokensDto
                .builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public UsernamePasswordAuthenticationToken parseAccessToken(String header) {
        if (header == null) {
            throw new AppTokensException("Header is null");
        }

        if (!header.startsWith(tokensPrefix)) {
            throw new AppTokensException("Token has invalid syntax");
        }

        var token = header.replaceAll(tokensPrefix, "");
        var userId = id(token);

        return userRepository
                .findById(userId)
                .map(user -> {
                    var userCredentials = user.toGetUserAuthorization();
                    return new UsernamePasswordAuthenticationToken(
                            userCredentials.getUsername(),
                            null,
                            of(new SimpleGrantedAuthority(userCredentials.getRole().toString()))
                    );
                }).orElseThrow(() -> new AppTokensException("Authorization failed"));
    }

    private Claims claims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     *
     * @param token we want to get id of user from
     * @return id of user
     */
    private Long id(String token) {
        return Long.parseLong(claims(token).getSubject());
    }

    /**
     *
     * @param token we want to get date from
     * @return expiration date
     */
    private Date expirationDate(String token) {
        return claims(token).getExpiration();
    }

    /**
     *
     * @param token we want to validate
     * @return true if token is before current date, otherwise return false
     */
    private boolean isTokenNotValid(String token) {
        return expirationDate(token).before(new Date());
    }

    /**
     *
     * @param refreshTokenDto we get
     * @return refresh token
     */
    public TokensDto refreshTokens(RefreshTokenDto refreshTokenDto) {
        if (refreshTokenDto == null) {
            throw new AppTokensException("Refresh token object is null");
        }

        var refreshToken = refreshTokenDto.getToken();
        if (refreshToken == null) {
            throw new AppTokensException("Token is null");
        }

        if (isTokenNotValid(refreshToken)) {
            throw new AppTokensException("Refresh token has been expired");
        }

        var accessTokenExpirationTimeMsFromToken = accessTokenExpirationTimeMs(refreshToken);
        if (accessTokenExpirationTimeMsFromToken < currentTimeMillis()) {
            throw new AppTokensException("Cannot refresh tokens because of access token expiration");
        }

        var userId = id(refreshToken);
        var currentDate = new Date();
        var accessTokenExpirationDate = new Date(currentDate.getTime() + accessTokenExpirationTimeMs);
        var refreshTokenExpirationDate = expirationDate(refreshToken);

        var accessToken = Jwts
                .builder()
                .setSubject(valueOf(userId))
                .setExpiration(accessTokenExpirationDate)
                .setIssuedAt(currentDate)
                .signWith(secretKey)
                .compact();

        var newRefreshToken = Jwts
                .builder()
                .setSubject(valueOf(userId))
                .setExpiration(refreshTokenExpirationDate)
                .setIssuedAt(currentDate)
                .claim(refreshTokenProperty, accessTokenExpirationDate.getTime())
                .signWith(secretKey)
                .compact();

        return TokensDto
                .builder()
                .accessToken(accessToken)
                .refreshToken(newRefreshToken)
                .build();
    }

    /**
     *
     * @param token we get
     * @return expiration time of token we get
     */
    private long accessTokenExpirationTimeMs(String token) {
        return claims(token).get(refreshTokenProperty, Long.class);
    }
}