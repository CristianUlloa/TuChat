package tuchat.server.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "tuchat.server.repository")
@EntityScan("tuchat.server.model")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
	