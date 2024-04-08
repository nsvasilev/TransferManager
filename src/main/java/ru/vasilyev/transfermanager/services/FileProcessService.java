package ru.vasilyev.transfermanager.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vasilyev.transfermanager.component.FileParser;
import ru.vasilyev.transfermanager.component.FileValidator;

import java.util.List;

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

    public void processFile(String fileName){
        // Валидация файла проверяет файл на соответствие структуре и расширению
        // расширение
        // структура
        if(!fileValidator.checkFileStructure(fileName) || !fileValidator.checkFileExtension(fileName)){
            log.info("Файл не прошёл валидацию");
            return;
        }




        //Чтению файла. fileName
        List<String> list = fileParser.readFile(fileName);


        log.info("Прочитал файл");
        log.info(String.join("\n", list));


        // Закидывание файла в базу.
    }

}
