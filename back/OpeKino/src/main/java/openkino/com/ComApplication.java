package openkino.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableAsync
@ComponentScan("openkino")
@SpringBootApplication
public class ComApplication {
    public static void main(String[] args) {
        SpringApplication.run(ComApplication.class, args);
    }

}
