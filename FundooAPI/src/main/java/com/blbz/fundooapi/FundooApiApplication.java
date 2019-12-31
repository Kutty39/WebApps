package com.blbz.fundooapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.blbz.fundooapi")
public class FundooApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FundooApiApplication.class, args);
    }

}
