package ru.vasilyev.transfermanager.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class FileValidator {

    public void validateFile(String fileName) {
        String s = fileName.split("\\.")[1];
        log.info("Проверяем файл: " + fileName);
        log.info("Расширение файла: " + s);
    }



}
