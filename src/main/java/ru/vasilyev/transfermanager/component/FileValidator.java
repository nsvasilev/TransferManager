package ru.vasilyev.transfermanager.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Component
public class FileValidator {

    public void validateFile(String fileName)  {
        String s = fileName.split("\\.")[1];
        log.info("Проверяем файл: " + fileName);
        log.info("Расширение файла: " + s);
        if (s.equals("txt")) {

            //ошибка в реализации. Исправить.
            //после запуска. 1.Создаем файл exe.txt, 2. Он автоматически перемещается и приходит уведомление.
            //При создании файла до запуска программы внутри тестовой папки, файл во время запуска программы перемещен не будет.
            //Файл успешно перенаправляется. На данный момент в режиме теста стоит расширение txt.
            try {
                Path soursePath = Paths.get("C:\\Users\\nikva\\OneDrive\\Рабочий стол\\java projects\\ttest\\" + fileName),
                        destPath = Paths.get("C:\\Users\\nikva\\OneDrive\\Рабочий стол\\java projects\\watchFile\\ppp\\" + fileName);
                Files.move(soursePath, destPath);
            } catch (IOException e) {
                System.out.println("ошибка ебаная");
            }
            log.info("Файл нужного расширения.Перенаправляю ");
        }
        else {
            log.info("файл другого расширения, error");
        }
    }



}
