package ru.vasilyev.transfermanager.services.fileProcessServices;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vasilyev.transfermanager.component.parser.ExcelParser;
import ru.vasilyev.transfermanager.component.validator.ExcelValidator;
import ru.vasilyev.transfermanager.dto.BankUserDto;
import ru.vasilyev.transfermanager.entity.BankUserEntity;
import ru.vasilyev.transfermanager.property.FileSystemWatcherProperties;
import ru.vasilyev.transfermanager.repostitory.BankUserRepository;
import ru.vasilyev.transfermanager.utils.FileMover;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
@Slf4j
@Service
public class XlsxFileProcessService extends AbstractFileProcessService {

    private final ExcelValidator excelValidator;
    private final ExcelParser excelParser;
    private final BankUserRepository bankUserRepository;
    private final FileMover fileMover;
    private final FileSystemWatcherProperties fileSystemWatcherProperties;

    public XlsxFileProcessService(FileMover fileMover, ExcelValidator excelValidator, ExcelParser excelParser, BankUserRepository bankUserRepository, FileSystemWatcherProperties fileSystemWatcherProperties) {
        super(fileMover);
        this.excelValidator = excelValidator;
        this.excelParser = excelParser;
        this.bankUserRepository = bankUserRepository;
        this.fileSystemWatcherProperties = fileSystemWatcherProperties;
        this.fileMover=fileMover;
    }

    @Override
    public void processFile(File file1) throws Exception {
        if  (excelValidator.checkStructure(file1)) {
            //TODO: указать имя файла, лог события должен быть после события(кроме выбрасывания исключений ). Ты перемещаешь файл только через 5 строк кода
            //successMoving(fileName, excelParser.readFile(fileName), bankUserRepository, fileSystemWatcherProperties);
            List<BankUserDto> bankUsersDtoList = excelParser.readFile(file1);
            List<BankUserEntity> bankUsersEntityList = bankUsersDtoList.stream().map(user -> new BankUserEntity(user.getFirstname(), user.getLastname(), user.getPatronymic(), user.getGender(), user.getBirthDate(), user.getBalance())).toList();
            bankUserRepository.saveAll(bankUsersEntityList);
         fileMover.moveToTargetDirectory(file1,fileSystemWatcherProperties.successPathDirectory());
            log.info("Файл " +file1 + " прошел валидацию. Перемещаю в success");
        } else {
            fileMover.moveToTargetDirectory(file1,fileSystemWatcherProperties.errorPathDirectory());
            log.info("Файл " + file1 + " не прошёл вторичную валидацию. Неправильная структура");
        }
    }

    @Override
    public String getExtension() {
        return "xlsx";
    }
}
