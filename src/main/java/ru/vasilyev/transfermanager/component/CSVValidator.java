package ru.vasilyev.transfermanager.component;

import au.com.bytecode.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.vasilyev.transfermanager.interfaces.FileValidator;

import java.io.FileReader;
import java.io.IOException;

import static ru.vasilyev.transfermanager.constants.DirectoryPaths.PROCESS_PATH;

/**
 * КОММЕНТАРИИИИ НАДА АСТАВЛЯТЬ!
 */
@Component
@Slf4j
public class CSVValidator implements FileValidator {

    @Override
    public boolean checkStructure(String fileName) {
        CSVReader csvReader = null;
        String[] values = null;
        try {
            csvReader = new CSVReader(new FileReader(PROCESS_PATH + fileName));
            while ((values = csvReader.readNext()) == null) ;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return values.length == 6;

    }

    @Override
    public boolean checkExtension(String fileName) {
        log.info("Проверяем файл: " + fileName);
        log.info("Расширение файла:csv.");
        return fileName.endsWith(".csv");
    }
}
