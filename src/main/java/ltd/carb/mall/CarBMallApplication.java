package ltd.carb.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

@MapperScan("ltd.carb.mall.dao")
@SpringBootApplication
public class CarBMallApplication {
    public static void main(String[] args) {

        SpringApplication.run(CarBMallApplication.class, args);
    }

    @Bean
    public CommandLineRunner flywayCleanMigrate(Flyway flyway) {
        return args -> {
            flyway.clean();
            flyway.migrate();
        };
    }
}
