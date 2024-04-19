package ru.vasilyev.transfermanager.component;

import au.com.bytecode.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.vasilyev.transfermanager.dto.BankUserDto;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static ru.vasilyev.transfermanager.constants.DirectoryPaths.PROCESS_PATH;
@Component
@Slf4j
public class CSVValidator {
    public boolean Checktructure (String fileName) throws IOException {
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader(PROCESS_PATH + fileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String[] values = null;
        while ((values = csvReader.readNext()) == null);
        return values.length ==6;

    }

    public boolean CheckExtension (String fileName) {
        log.info("Проверяем файл: " + fileName);
        log.info("Расширение файла:csv.");
        return fileName.endsWith(".csv");
    }
}
