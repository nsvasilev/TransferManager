package ru.vasilyev.transfermanager.component;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.vasilyev.transfermanager.dto.BankUserDto;
import ru.vasilyev.transfermanager.interfaces.FileParser;
import ru.vasilyev.transfermanager.property.FileSystemWatcherProperties;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
@Component
@Slf4j
public class CSVParser implements FileParser {
    private final FileSystemWatcherProperties fileSystemWatcherProperties;

    public CSVParser(FileSystemWatcherProperties fileSystemWatcherProperties) {
        this.fileSystemWatcherProperties = fileSystemWatcherProperties;
    }
    @Override
    public List<BankUserDto> readFile(String fileName) {

        List<BankUserDto> fileData = null;
        try (var bufferedReader = Files.newBufferedReader(Path.of(fileSystemWatcherProperties.processPathDirectory()+fileName), StandardCharsets.UTF_8)) {

            ColumnPositionMappingStrategy<BankUserDto> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(BankUserDto.class);
            String[] fields = {"firstname", "lastname", "patronymic", "gender", "birthday", "balance"};
            strategy.setColumnMapping(fields);

            CsvToBean<BankUserDto> csvToBean = new CsvToBeanBuilder<BankUserDto>(bufferedReader)
                    .withMappingStrategy(strategy)
                    .withType(BankUserDto.class)
                    .withThrowExceptions(true)
                    .withSkipLines(1)
                    .build();

             fileData = csvToBean.parse();
        } catch (Exception e) {
            log.info("ошибка в чтении csv файла " + e.getMessage());
            e.printStackTrace();
        }

        return fileData;
    }

}