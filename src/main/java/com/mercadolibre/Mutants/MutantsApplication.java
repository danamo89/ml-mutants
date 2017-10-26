package com.mercadolibre.Mutants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.mercadolibre")
public class MutantsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MutantsApplication.class, args);
    }
}
