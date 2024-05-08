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
    public void processFile(File file) throws Exception {
        if  (excelValidator.checkStructure(file)) {
            //TODO: указать имя файла, лог события должен быть после события(кроме выбрасывания исключений ). Ты перемещаешь файл только через 5 строк кода
            //successMoving(fileName, excelParser.readFile(fileName), bankUserRepository, fileSystemWatcherProperties);
            List<BankUserDto> bankUsersDtoList = excelParser.readFile(file);
            List<BankUserEntity> bankUsersEntityList = bankUsersDtoList.stream().map(user -> new BankUserEntity(user.getFirstname(), user.getLastname(), user.getPatronymic(), user.getGender(), user.getBirthDate(), user.getBalance())).toList();
            bankUserRepository.saveAll(bankUsersEntityList);
         fileMover.moveToTargetDirectory(file,fileSystemWatcherProperties.successPathDirectory());
            log.info("Файл " +file + " прошел валидацию. Перемещаю в success");
        } else {
            log.info("Файл " + file + " не прошёл вторичную валидацию. Неправильная структура");
//            Path process = Paths.get(fileSystemWatcherProperties.processPathDirectory() + fileName);
//            Path error = Paths.get(fileSystemWatcherProperties.errorPathDirectory() + fileName);
//            Files.move(process, error);
        }
    }

    @Override
    public String getExtension() {
        return "xlsx";
    }
}
