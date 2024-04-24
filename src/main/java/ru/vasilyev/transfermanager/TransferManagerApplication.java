package ru.vasilyev.transfermanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableAsync;


/**
 * TODO: НЕ ХВАТАЕТ ЛОГОВ В ПРОЕКТЕ.
 */
@EnableAsync
@SpringBootApplication
@ConfigurationPropertiesScan
public class TransferManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TransferManagerApplication.class, args);
    }
}
