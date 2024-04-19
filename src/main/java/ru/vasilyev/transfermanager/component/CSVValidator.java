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

    /**
     * 1. I/O стримы - потоки чтения и записи. Соответственно, для каждого файла, открывается некоторое соединение, называемое файловым дескриптром.
     * Либо в блоке finally вызываем у стрима io метод .close() либо try с ресурсами - try with resources.
     * 2. Все логи пишем только после события.
     * 3. Везде где есть блок catch - Логируем какую ошибку поймали
     *
     */
    @Override
    public boolean checkStructure(String fileName) throws IOException {
        CSVReader csvReader = null;
        String[] values;
        try {
            csvReader = new CSVReader(new FileReader(PROCESS_PATH + fileName));
            while ((values = csvReader.readNext()) == null) ;
        } catch (IOException e) {
            log.info("Не удалось проверить структуру файла, по причине: " + e.getMessage());
            throw new RuntimeException(e);
        }
        finally {
            csvReader.close();
        }
        return values.length == 6;
    }

    @Override
    public boolean checkExtension(String fileName) {
        log.info("Проверяем файл: " + fileName);
        log.info("Расширение файла:csv");
        return fileName.endsWith(".csv");
    }
}
