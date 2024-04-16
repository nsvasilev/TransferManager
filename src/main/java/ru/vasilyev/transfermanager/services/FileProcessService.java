package ru.vasilyev.transfermanager.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import ru.vasilyev.transfermanager.component.FileParser;
import ru.vasilyev.transfermanager.component.FileValidator;
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

    private final FileParser fileParser;
    private final FileValidator fileValidator;
    private final BankUserRepository bankUserRepository;
    private final FileSystemWatcherProperties fileSystemWatcherProperties;

    @Autowired
    public FileProcessService(FileParser fileParser, FileValidator fileValidator, BankUserRepository bankUserRepository, FileSystemWatcherProperties fileSystemWatcherProperties) {
        this.fileParser = fileParser;
        this.fileValidator = fileValidator;
        this.bankUserRepository = bankUserRepository;
        this.fileSystemWatcherProperties = fileSystemWatcherProperties;
    }

    public void processFile(String fileName) throws IOException {
        if (!fileValidator.checkFileExtension(fileName) || !fileValidator.checkFileStructure(fileName)) {
            log.info("Файл не прошёл валидацию");
            Path Directory = Paths.get(fileSystemWatcherProperties.Process() + fileName);
            Path destPath = Paths.get(fileSystemWatcherProperties.Error() + fileName);
            Files.move(Directory, destPath);
            return;
        }


        //Чтению файла. fileName
        List<BankUserDto> bankUsersDtoList = fileParser.readFile(fileName);

        List<BankUserEntity> bankUsersEntityList = bankUsersDtoList.stream().map(user -> new BankUserEntity(user.getFirstname(), user.getLastname(), user.getPatronymic(), user.getGender(), user.getBirthDate(),user.getBalance())).toList();

        bankUserRepository.saveAll(bankUsersEntityList);

        log.info("Файл прошел валидацию. Перемещаю в success");
        Path Directory = Paths.get(fileSystemWatcherProperties.Process() + fileName);
        Path Success = Paths.get(fileSystemWatcherProperties.Success() + fileName);
        Files.move(Directory, Success);
    }
}
