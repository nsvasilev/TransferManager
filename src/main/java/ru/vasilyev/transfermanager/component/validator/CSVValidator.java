package ru.vasilyev.transfermanager.component.validator;

import au.com.bytecode.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vasilyev.transfermanager.property.FileSystemWatcherProperties;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * CSVValidator - это класс, предназначенный для проверки CSV файла на его структуру.
 */
@Component
@Slf4j
public class CSVValidator implements FileValidator {
    private final FileSystemWatcherProperties fileSystemWatcherProperties;
    // филд, который не используется в дальнейшем

    @Autowired
    public CSVValidator(FileSystemWatcherProperties fileSystemWatcherProperties) {
        this.fileSystemWatcherProperties = fileSystemWatcherProperties;
        //конструктор с филдом ранее, чтобы была создана зависимость через конструктор.
    }

    /**
     * 1. I/O стримы - потоки чтения и записи. Соответственно, для каждого файла, открывается некоторое соединение, называемое файловым дескриптром.
     * Либо в блоке finally вызываем у стрима io метод .close() либо try с ресурсами - try with resources.
     * 2. Все логи пишем только после события.
     * 3. Везде где есть блок catch - Логируем какую ошибку поймали
     */

    /**
     * ниже описан метод CheckStructure, который в параметрах принимает файл
     * используется try with resources, где в качестве ресурса принимается сам csv файл.
     * в блоке catch указывается обработка ошибки соответсвенно
     *
     */
    @Override
    public boolean checkStructure(File file) {
        String[] values; //создается стринговый массив values.
        try (CSVReader csvReader = new CSVReader(new FileReader(file))) {
            while ((values = csvReader.readNext()) == null) ; //считывание кол-ва эл-ов строки до тех пор, пока там есть хоть что-то.
        } catch (IOException e) {
            log.info("Не удалось проверить структуру файла, по причине: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return values.length == 6; //возвращаем длину массива values.
    }

}
