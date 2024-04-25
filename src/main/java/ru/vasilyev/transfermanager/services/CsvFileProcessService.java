package ru.vasilyev.transfermanager.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vasilyev.transfermanager.component.*;
import ru.vasilyev.transfermanager.dto.BankUserDto;
import ru.vasilyev.transfermanager.entity.BankUserEntity;
import ru.vasilyev.transfermanager.property.FileSystemWatcherProperties;
import ru.vasilyev.transfermanager.repostitory.BankUserRepository;
import ru.vasilyev.transfermanager.utils.FileMover;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * TODO: Код выглядит очень громоздко и некрасиво.
 *  Исправлять можно по разному. Можно выносить общие логически блоки в отдельные методы, например перемещение файла
 *  Или применять паттерны проектирования. До них дойдём чуть позже.
 *  Более того, у тебя тут есть даже лишний код
 */
@Service
@Slf4j
public class CsvFileProcessService implements IFileProcessService {


    private final CSVParser csvParser;
    private final CSVValidator csvValidator;
    private final BankUserRepository bankUserRepository;
    private final FileSystemWatcherProperties fileSystemWatcherProperties;
    private final ErrorHandler errorHandler;


    @Autowired
    public CsvFileProcessService(CSVParser csvParser, CSVValidator csvValidator, BankUserRepository bankUserRepository, FileSystemWatcherProperties fileSystemWatcherProperties, ErrorHandler errorHandler) {
        this.csvParser = csvParser;
        this.csvValidator = csvValidator;
        this.bankUserRepository = bankUserRepository;
        this.fileSystemWatcherProperties = fileSystemWatcherProperties;
        this.errorHandler = errorHandler;
    }


    public void processFile(String fileName) throws IOException {
        if (csvValidator.checkExtension(fileName) && csvValidator.checkStructure(fileName)) {
            List<BankUserDto> bankUsersDtoList = csvParser.readFile(fileName);
            List<BankUserEntity> bankUsersEntityList = bankUsersDtoList.stream().map(user -> new BankUserEntity(user.getFirstname(), user.getLastname(), user.getPatronymic(), user.getGender(), user.getBirthDate(), user.getBalance())).toList();
            bankUserRepository.saveAll(bankUsersEntityList);
            FileMover.moveToSuccess(fileName);
            log.info("Файл " + fileName + " прошел валидацию. Перемещаю в success");
        } else {
            log.info("Файл " + fileName + " не прошёл вторичную валидацию. Неправильная структура");
            Path process = Paths.get(fileSystemWatcherProperties.processPathDirectory() + fileName);
            Path error = Paths.get(fileSystemWatcherProperties.errorPathDirectory() + fileName);
            Files.move(process, error);
            errorHandler.clearError(fileName);
            log.info("Файл " + fileName + " был направлен в папку Error");
        }

    }

    @Override
    public String getExtension() {
        return "csv";
    }
}

