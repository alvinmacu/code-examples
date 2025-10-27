package mx.com.alvin.sqsmessagingstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SqsMessagingManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SqsMessagingManagerApplication.class, args);
    }

}
