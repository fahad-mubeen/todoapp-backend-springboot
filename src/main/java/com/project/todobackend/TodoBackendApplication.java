package com.project.todobackend;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.File;

@EnableJpaAuditing
@SpringBootApplication
public class TodoBackendApplication {

    public static void main(String[] args) {
        if (new File(".env").exists()) {
            Dotenv dotenv = Dotenv.load();
            dotenv.entries().forEach(entry ->
                    System.setProperty(entry.getKey(), entry.getValue())
            );
        } else {
            System.out.println("Running in production mode - using environment variables");
        }
        SpringApplication.run(TodoBackendApplication.class, args);
    }

}
