package ru.vasilyev.transfermanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableAsync
@SpringBootApplication
public class TransferManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TransferManagerApplication.class, args);
    }
}
