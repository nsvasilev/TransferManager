package ru.vasilyev.transfermanager.component.validator;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * ExcelValidator - класс, предназначенный для проверки Excel файла на структуру.
 * Используется булеан метод checkStructure , который в параметрах принимает файл.
 * Используется try with resources.
 * Используется обработка ошибок.
 */
@Slf4j
@Component
public class ExcelValidator implements FileValidator {
    @Override
    public boolean checkStructure(File file) {
        int cellsNum; //переменная для подсчета столбцов в файле
        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {
            cellsNum = workbook.getSheetAt(0).getRow(0).getPhysicalNumberOfCells();
            // сразу придаем значение ранее созданной переменной
        } catch (IOException ex) {
            log.info("ошибка при проверке структуры файла" + ex.getMessage());
            throw new RuntimeException();
        }
        return cellsNum == 6; //возвращаем try или false.
    }
}
