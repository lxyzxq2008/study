package com.sunroom;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConfigEncryptApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ConfigEncryptApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("app starter");
    }
}
