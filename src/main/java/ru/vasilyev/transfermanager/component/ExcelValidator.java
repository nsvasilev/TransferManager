package ru.vasilyev.transfermanager.component;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import ru.vasilyev.transfermanager.property.FileSystemWatcherProperties;

import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
@Component
public class ExcelValidator implements FileValidator {
    private final FileSystemWatcherProperties fileSystemWatcherProperties;//добавили и используем дальше

    public ExcelValidator(FileSystemWatcherProperties fileSystemWatcherProperties) {
        this.fileSystemWatcherProperties = fileSystemWatcherProperties;
    }

    @Override
    public boolean checkStructure(String fileName) {
        int cellsNum;
        try (FileInputStream fis = new FileInputStream(fileSystemWatcherProperties.processPathDirectory() + fileName);
             Workbook workbook = new XSSFWorkbook(fis)) {
            cellsNum = workbook.getSheetAt(0).getRow(0).getPhysicalNumberOfCells();
        } catch (IOException ex) {
            log.info("ошибка при проверке структуры файла" + ex.getMessage());
            throw new RuntimeException();
        }
        return cellsNum == 6;
    }

    //метод переписан
    @Override
    public boolean checkExtension(String fileName) {
        log.info("Проверяем файл: " + fileName);
        log.info("Расширение файла: xlsx");
        return (fileName.endsWith(".xlsx"));
    }
}
