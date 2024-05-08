package ru.vasilyev.transfermanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Класс с контроллером для работы в локальной сети.
 */
@RestController
public class Controller {

    @GetMapping
    public String method(){
        return "Method";
    }
}
