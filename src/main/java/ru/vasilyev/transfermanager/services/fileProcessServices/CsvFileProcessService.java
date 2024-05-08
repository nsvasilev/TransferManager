package ru.vasilyev.transfermanager.services.fileProcessServices;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vasilyev.transfermanager.component.*;
import ru.vasilyev.transfermanager.component.parser.CSVParser;
import ru.vasilyev.transfermanager.component.validator.CSVValidator;
import ru.vasilyev.transfermanager.dto.BankUserDto;
import ru.vasilyev.transfermanager.entity.BankUserEntity;
import ru.vasilyev.transfermanager.property.FileSystemWatcherProperties;
import ru.vasilyev.transfermanager.repostitory.BankUserRepository;
import ru.vasilyev.transfermanager.utils.FileMover;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * CsvFileProcessService - класс, необходимый для проверки Csv файла на валидацию и для внесения этого самого файла в бд
 * в противном случае файл перемещается в Error
 */
@Service
@Slf4j
public class CsvFileProcessService extends AbstractFileProcessService {


    private final CSVParser csvParser;
    private final CSVValidator csvValidator;
    private final BankUserRepository bankUserRepository;
   private final FileMover fileMover;
    private final FileSystemWatcherProperties fileSystemWatcherProperties;


    @Autowired
    public CsvFileProcessService(FileMover fileMover, CSVParser csvParser, CSVValidator csvValidator, BankUserRepository bankUserRepository, FileSystemWatcherProperties fileSystemWatcherProperties, ErrorHandler errorHandler, FileMover fileMover1, FileSystemWatcherProperties fileSystemWatcherProperties1) {
        super(fileMover);
        this.csvParser = csvParser;
        this.csvValidator = csvValidator;
        this.bankUserRepository = bankUserRepository;
        this.fileMover = fileMover1;
        this.fileSystemWatcherProperties = fileSystemWatcherProperties1;
    }


    public void processFile(File file) throws IOException {
        if  (csvValidator.checkStructure(file)) {
            List<BankUserDto> bankUsersDtoList = csvParser.readFile(file);
            List<BankUserEntity> bankUsersEntityList = bankUsersDtoList.stream().map(user -> new BankUserEntity(user.getFirstname(), user.getLastname(), user.getPatronymic(), user.getGender(), user.getBirthDate(), user.getBalance())).toList();
            bankUserRepository.saveAll(bankUsersEntityList);
            fileMover.moveToTargetDirectory(file,fileSystemWatcherProperties.successPathDirectory());
            log.info("Файл " + file + " прошел валидацию. Перемещаю в success");
        } else {
            log.info("Файл " + file + " не прошёл вторичную валидацию. Неправильная структура");
            log.info("Файл " + file + " был направлен в папку Error");
        }

    }

    @Override
    public String getExtension() {
        return "csv";
    }
}


