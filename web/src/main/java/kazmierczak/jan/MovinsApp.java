package kazmierczak.jan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MovinsApp {
    public static void main(String[] args) {
        SpringApplication.run(MovinsApp.class, args);
    }
}
