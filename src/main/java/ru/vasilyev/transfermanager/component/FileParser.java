package ru.vasilyev.transfermanager.component;


import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import ru.vasilyev.transfermanager.entities.FileInfo;

import javax.xml.crypto.Data;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.apache.poi.hssf.usermodel.HeaderFooter.file;
import static ru.vasilyev.transfermanager.constants.DirectoryPaths.DIRECTORY_PATH;


/**
 * Собрали ExcelParser для чтения excel файла. После чтения, данные идут в эррейлист.
 * Вопрос: для чего они идут в эррей лист?
 **/

@Component
public class FileParser {
    public List<FileInfo> readFile(String fileName) {
        try (FileInputStream fis = new FileInputStream(DIRECTORY_PATH + fileName)) {
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            List<FileInfo> fileData = new ArrayList<FileInfo>();
            DataFormatter dataFormatter = new DataFormatter();
            for (int n = 1; n < sheet.getPhysicalNumberOfRows(); n++) {
                Row row = sheet.getRow(n);
                FileInfo fileInfo = new FileInfo();
                int i = row.getFirstCellNum();
                fileInfo.setName(dataFormatter.formatCellValue(row.getCell(i)));
                fileInfo.setSoname(dataFormatter.formatCellValue(row.getCell(++i)));
                fileInfo.setPatronymic(dataFormatter.formatCellValue(row.getCell(++i)));
                fileInfo.setGender(dataFormatter.formatCellValue(row.getCell(i)));
                fileInfo.setBirthday((int) row.getCell(++i).getNumericCellValue());
                fileData.add(fileInfo);
            }
            // Заполнен строками Фамилия Имя Отчество Гендер
            return new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

