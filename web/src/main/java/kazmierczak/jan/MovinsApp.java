package kazmierczak.jan;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import javax.crypto.SecretKey;

import static io.jsonwebtoken.SignatureAlgorithm.*;
import static io.jsonwebtoken.security.Keys.*;
import static org.springframework.boot.SpringApplication.*;
import static org.springframework.security.crypto.factory.PasswordEncoderFactories.*;

@SpringBootApplication
@EnableAsync
public class MovinsApp {
    public static void main(String[] args) {
        run(MovinsApp.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return createDelegatingPasswordEncoder();
    }

    @Bean
    public SecretKey secretKey() {
        return secretKeyFor(HS512);
    }
}
