package ru.vasilyev.transfermanager.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vasilyev.transfermanager.component.FileParser;
import ru.vasilyev.transfermanager.component.FileValidator;
import ru.vasilyev.transfermanager.dto.FileInfo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static ru.vasilyev.transfermanager.constants.DirectoryPaths.*;

@Service
@Slf4j
public class FileProcessService {

    private final FileParser fileParser;
    private final FileValidator fileValidator;

    @Autowired
    public FileProcessService(FileParser fileParser, FileValidator fileValidator) {
        this.fileParser = fileParser;
        this.fileValidator = fileValidator;
    }

    public void processFile(String fileName) throws IOException {
        // Валидация файла проверяет файл на соответствие структуре и расширению
        // расширение
        // структура
        if (!fileValidator.checkFileExtension(fileName) || !fileValidator.checkFileStructure(fileName)) {
            log.info("Файл не прошёл валидацию");
//            Path Directory = Paths.get(DIRECTORY_PATH + fileName);
//            Path destPath = Paths.get(ERROR_PATH + fileName);
//            Files.move(Directory, destPath);
            return;
        }
        log.info("Файл прошел валидацию. Перемещаю в success");
//        Path Directory = Paths.get(DIRECTORY_PATH + fileName);
//        Path Success = Paths.get(SUCCESS_PATH + fileName);
//        Files.move(Directory, Success);


        //Чтению файла. fileName
        List<FileInfo> list = fileParser.readFile(fileName);


        log.info("Прочитал файл");
        //log.info(String.join("\n", (Iterable<? extends CharSequence>) list));


        // Закидывание файла в базу.
    }

}
