package ru.vasilyev.transfermanager.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import ru.vasilyev.transfermanager.component.CSVParser;
import ru.vasilyev.transfermanager.component.ExcelParser;
import ru.vasilyev.transfermanager.component.ExcelValidator;
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
    private final CSVParser csvParser;//!!!!
    private final ExcelValidator excelValidator;
    private final BankUserRepository bankUserRepository;
    private final FileSystemWatcherProperties fileSystemWatcherProperties;

    @Autowired
    public FileProcessService(ExcelParser excelParser, CSVParser csvParser, ExcelValidator excelValidator, BankUserRepository bankUserRepository, FileSystemWatcherProperties fileSystemWatcherProperties) {
        this.excelParser = excelParser;
        this.csvParser = csvParser;//!!!
        this.excelValidator = excelValidator;
        this.bankUserRepository = bankUserRepository;
        this.fileSystemWatcherProperties = fileSystemWatcherProperties;
    }

    public void processFile(String fileName) throws IOException {
        if (!excelValidator.checkExtension(fileName) || !excelValidator.checkStructure(fileName)) {
            log.info("Файл не прошёл валидацию");
            Path Directory = Paths.get(fileSystemWatcherProperties.Process() + fileName);
            Path destPath = Paths.get(fileSystemWatcherProperties.Error() + fileName);
            Files.move(Directory, destPath);
            return;
        }


        //Чтению файла. fileName
        if (fileName.endsWith(".xlsx")) {
        List<BankUserDto> bankUsersDtoList = excelParser.readFile(fileName);


        List<BankUserEntity> bankUsersEntityList = bankUsersDtoList.stream().map(user -> new BankUserEntity(user.getFirstname(), user.getLastname(), user.getPatronymic(), user.getGender(), user.getBirthDate(),user.getBalance())).toList();

        bankUserRepository.saveAll(bankUsersEntityList);}
        if (fileName.endsWith(".csv")) {
           // List<BankUserDto> bankUsersDtoList = csvParser.readFile(fileName);
          //  List<BankUserEntity> bankUsersEntityList = bankUsersDtoList.stream().map(user -> new BankUserEntity(user.getFirstname(), user.getLastname(), user.getPatronymic(), user.getGender(), user.getBirthDate(),user.getBalance())).toList();

            //bankUserRepository.saveAll(bankUsersEntityList);

        }

        log.info("Файл прошел валидацию. Перемещаю в success");
        Path Directory = Paths.get(fileSystemWatcherProperties.Process() + fileName);
        Path Success = Paths.get(fileSystemWatcherProperties.Success() + fileName);
        Files.move(Directory, Success);
    }
}
