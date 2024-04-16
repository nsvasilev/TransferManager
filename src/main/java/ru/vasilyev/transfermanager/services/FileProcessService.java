package ru.vasilyev.transfermanager.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vasilyev.transfermanager.component.FileParser;
import ru.vasilyev.transfermanager.component.FileValidator;
import ru.vasilyev.transfermanager.dto.BankUserDto;
import ru.vasilyev.transfermanager.entity.BankUserEntity;
import ru.vasilyev.transfermanager.repostitory.BankUserRepository;

import java.util.List;

@Service
@Slf4j
public class FileProcessService {

    private final FileParser fileParser;
    private final FileValidator fileValidator;
    private final BankUserRepository bankUserRepository;

    @Autowired
    public FileProcessService(FileParser fileParser, FileValidator fileValidator, BankUserRepository bankUserRepository) {
        this.fileParser = fileParser;
        this.fileValidator = fileValidator;
        this.bankUserRepository = bankUserRepository;
    }

    public void processFile(String fileName) {
        if (!fileValidator.checkFileExtension(fileName) || !fileValidator.checkFileStructure(fileName)) {
            log.info("Файл не прошёл валидацию");
//            Path Directory = Paths.get(DIRECTORY_PATH + fileName);
//            Path destPath = Paths.get(ERROR_PATH + fileName);
//            Files.move(Directory, destPath);
            return;
        }
        log.info("Файл прошел валидацию. Перемещаю в success");
//        Path Directory = Paths.get(DIRECTORY_PATH + fileName);
//        Path Success = Paths.get(SUCCESS_PATH + fileName);
//        Files.move(Directory, Success);

        //Чтению файла. fileName
        List<BankUserDto> bankUsersDtoList = fileParser.readFile(fileName);

        List<BankUserEntity> bankUsersEntityList = bankUsersDtoList.stream().map(user -> new BankUserEntity(user.getFirstname(), user.getLastname(), user.getPatronymic(), user.getGender(), user.getBirthDate(),user.getBalance())).toList();

        bankUserRepository.saveAll(bankUsersEntityList);
    }

}
