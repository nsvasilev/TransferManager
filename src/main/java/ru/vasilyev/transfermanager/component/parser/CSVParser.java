package ru.vasilyev.transfermanager.component.parser;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.vasilyev.transfermanager.dto.BankUserDto;
import ru.vasilyev.transfermanager.property.FileSystemWatcherProperties;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

@Component
@Slf4j
public class CSVParser implements FileParser {
    private final FileSystemWatcherProperties fileSystemWatcherProperties;

    public CSVParser(FileSystemWatcherProperties fileSystemWatcherProperties) {
        this.fileSystemWatcherProperties = fileSystemWatcherProperties;
    }
    /**
    CSVParser -  Класс, предназнанченный для парсирования csv файлов.
     */
    @Override //аннотация переопрделения
    public List<BankUserDto> readFile(File fileName) {

        List<BankUserDto> fileData = null; //создается лист, типизированный классом BankUserDto и приравнивается к null.
        try (var bufferedReader = Files.newBufferedReader(fileName.toPath(), StandardCharsets.UTF_8))
        //создается tru-catch with resources.
        //в ресурсах создается bufferedReader, который считывает название файла.
        //если название файла удовлетворяет условие bufferedReader, выполняется блок try
        {
            ColumnPositionMappingStrategy<BankUserDto> strategy = new ColumnPositionMappingStrategy<>();
            //создается класс , который позволяет считывать csv файл.
            strategy.setType(BankUserDto.class);// устанавливается тип класса, который сопоставляется
            String[] fields = {"firstname", "lastname", "patronymic", "gender", "birthday", "balance"};
            //создается массив филдов
            strategy.setColumnMapping(fields); // не знаю как правильно это обьяснить

            /*
            Ниже создается класс csvToBean для преобразования данных csv в объекты.
             **/
            CsvToBean<BankUserDto> csvToBean = new CsvToBeanBuilder<BankUserDto>(bufferedReader)
                    .withMappingStrategy(strategy) //во время преобразования используется стратегия
                    .withType(BankUserDto.class) //указывается класс, по которому будет сопоставление данных
                    .withThrowExceptions(true) // в случае ошибок при обработке файла, будут выходить exception.
                    .withSkipLines(1) // пропускаем первую полосу в файле
                    .build(); // создаем что? ??

            fileData = csvToBean.parse(); // парсим все получившееся в ранее созданный лист.
        } catch (Exception e) {
            /**
             * обработка эксепшенов приведена ниже.
             */
            log.info("ошибка в чтении csv файла " + e.getMessage());
            e.printStackTrace();
        }

        return fileData; //возвращаем уже заполненный лист для дальнейшей работы с ним.
    }

}