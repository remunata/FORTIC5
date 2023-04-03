package com.pplbo.fortic5;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@RequiredArgsConstructor
@EnableTransactionManagement
public class Fortic5Application {

    public static void main(String[] args) {
        SpringApplication.run(Fortic5Application.class, args);
    }
}
