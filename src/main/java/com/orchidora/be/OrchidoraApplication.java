package com.orchidora.be;

import org.flywaydb.core.Flyway;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class OrchidoraApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrchidoraApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(DataSource dataSource) {
        return args -> Flyway.configure()
                .dataSource(dataSource)
                .baselineOnMigrate(true)
                .baselineVersion("0")
                .load().migrate();
    }
}
