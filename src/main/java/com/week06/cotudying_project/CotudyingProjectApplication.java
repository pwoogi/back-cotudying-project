package com.week06.cotudying_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CotudyingProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CotudyingProjectApplication.class, args);
    }

}
