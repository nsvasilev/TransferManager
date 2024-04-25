package ru.vasilyev.transfermanager.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vasilyev.transfermanager.component.ExcelParser;
import ru.vasilyev.transfermanager.component.ExcelValidator;
import ru.vasilyev.transfermanager.dto.BankUserDto;
import ru.vasilyev.transfermanager.entity.BankUserEntity;
import ru.vasilyev.transfermanager.property.FileSystemWatcherProperties;
import ru.vasilyev.transfermanager.repostitory.BankUserRepository;
import ru.vasilyev.transfermanager.utils.FileMover;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
@Slf4j
@Service
public class XlsxFileProcessService implements IFileProcessService {

    private final ExcelValidator excelValidator;
    private final ExcelParser excelParser;
    private final BankUserRepository bankUserRepository;
    private final FileSystemWatcherProperties fileSystemWatcherProperties;

    public XlsxFileProcessService(ExcelValidator excelValidator, ExcelParser excelParser, BankUserRepository bankUserRepository, FileSystemWatcherProperties fileSystemWatcherProperties) {
        this.excelValidator = excelValidator;
        this.excelParser = excelParser;
        this.bankUserRepository = bankUserRepository;
        this.fileSystemWatcherProperties = fileSystemWatcherProperties;
    }

    @Override
    public void processFile(String fileName) throws Exception {
        if (excelValidator.checkExtension(fileName) && excelValidator.checkStructure(fileName)) {
            //TODO: указать имя файла, лог события должен быть после события(кроме выбрасывания исключений ). Ты перемещаешь файл только через 5 строк кода
            //successMoving(fileName, excelParser.readFile(fileName), bankUserRepository, fileSystemWatcherProperties);
            List<BankUserDto> bankUsersDtoList = excelParser.readFile(fileName);
            List<BankUserEntity> bankUsersEntityList = bankUsersDtoList.stream().map(user -> new BankUserEntity(user.getFirstname(), user.getLastname(), user.getPatronymic(), user.getGender(), user.getBirthDate(), user.getBalance())).toList();
            bankUserRepository.saveAll(bankUsersEntityList);
            FileMover.moveToSuccess(fileName);
            log.info("Файл " +fileName + " прошел валидацию. Перемещаю в success");
        } else {
            log.info("Файл " + fileName + " не прошёл вторичную валидацию. Неправильная структура");
            Path process = Paths.get(fileSystemWatcherProperties.processPathDirectory() + fileName);
            Path error = Paths.get(fileSystemWatcherProperties.errorPathDirectory() + fileName);
            Files.move(process, error);
        }

    }
    @Override
    public String getExtension() {
        return "xlsx";
    }
}
