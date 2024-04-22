package ru.vasilyev.transfermanager.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import ru.vasilyev.transfermanager.component.*;
import ru.vasilyev.transfermanager.dto.BankUserDto;
import ru.vasilyev.transfermanager.entity.BankUserEntity;
import ru.vasilyev.transfermanager.property.FileSystemWatcherProperties;
import ru.vasilyev.transfermanager.repostitory.BankUserRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@Slf4j
@EnableConfigurationProperties(FileSystemWatcherProperties.class)
public class FileProcessService {

    private final ExcelParser excelParser;
    private final ExcelValidator excelValidator;
    private final CSVParser csvParser;//!!!!
    private final CSVValidator csvValidator;
    private final BankUserRepository bankUserRepository;
    private final FileSystemWatcherProperties fileSystemWatcherProperties;
    private final ErrorHandler errorHandler; //добавили обработчика ошибок

    @Autowired
    public FileProcessService(ExcelParser excelParser, CSVParser csvParser, CSVValidator csvValidator, ExcelValidator excelValidator, BankUserRepository bankUserRepository, FileSystemWatcherProperties fileSystemWatcherProperties, ErrorHandler errorHandler) {
        this.excelParser = excelParser;
        this.csvParser = csvParser;//!!!
        this.csvValidator = csvValidator;
        this.excelValidator = excelValidator;
        this.bankUserRepository = bankUserRepository;
        this.fileSystemWatcherProperties = fileSystemWatcherProperties;
        this.errorHandler = errorHandler;
    }


    public void processFile(String fileName) throws IOException {
        if (!fileName.endsWith(".csv") && !fileName.endsWith(".xlsx")) {
            log.info("Файл не прошёл первичную валидацию. Неправильное расширение");
            Path Directory = Paths.get(fileSystemWatcherProperties.processPathDirectory() + fileName);
            Path destPath = Paths.get(fileSystemWatcherProperties.errorPathDirectory() + fileName);
            Files.move(Directory, destPath);
            return;
        }
        if (fileName.endsWith(".xlsx")) {
            if (excelValidator.checkExtension(fileName) && excelValidator.checkStructure(fileName)) {
                log.info("Файл xlsx прошел валидацию. Перемещаю в success");
                List<BankUserDto> bankUsersDtoList = excelParser.readFile(fileName);
                List<BankUserEntity> bankUsersEntityList = bankUsersDtoList.stream().map(user -> new BankUserEntity(user.getFirstname(), user.getLastname(), user.getPatronymic(), user.getGender(), user.getBirthDate(), user.getBalance())).toList();
                bankUserRepository.saveAll(bankUsersEntityList);
                Path process = Paths.get(fileSystemWatcherProperties.processPathDirectory() + fileName);
                Path success = Paths.get(fileSystemWatcherProperties.successPathDirectory() + fileName);
                Files.move(process, success);
                return;
            } else {
                log.info("Файл" + fileName + " не прошёл вторичную валидацию. Неправильная структура");
                Path process = Paths.get(fileSystemWatcherProperties.processPathDirectory() + fileName);
                Path error = Paths.get(fileSystemWatcherProperties.errorPathDirectory() + fileName);
                Files.move(process, error);
            }

        }
        if (fileName.endsWith(".csv")) {
            if (csvValidator.checkExtension(fileName) && csvValidator.checkStructure(fileName)) {
                log.info(" Файл csv прошел валидацию. Перемещаю в success");
                List<BankUserDto> bankUsersDtoList = csvParser.readFile(fileName);
                List<BankUserEntity> bankUsersEntityList = bankUsersDtoList.stream().map(user -> new BankUserEntity(user.getFirstname(), user.getLastname(), user.getPatronymic(), user.getGender(), user.getBirthDate(), user.getBalance())).toList();
                bankUserRepository.saveAll(bankUsersEntityList);
                Path process = Paths.get(fileSystemWatcherProperties.processPathDirectory() + fileName);
                Path success = Paths.get(fileSystemWatcherProperties.successPathDirectory() + fileName);
                Files.move(process, success);
            } else {
                log.info(" Файл" + fileName + " не прошёл вторичную валидацию. Неправильная структура");
                Path process = Paths.get(fileSystemWatcherProperties.processPathDirectory() + fileName);
                Path error = Paths.get(fileSystemWatcherProperties.errorPathDirectory() + fileName);
                Files.move(process, error);
                errorHandler.clearError(fileName);
                log.info(" Файл" + fileName + "был направлен в папку Error");

            }
        }
    }
}

