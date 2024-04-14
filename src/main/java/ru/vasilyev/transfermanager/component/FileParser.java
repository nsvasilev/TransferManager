package ru.vasilyev.transfermanager.component;


import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import ru.vasilyev.transfermanager.dto.FileInfo;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static ru.vasilyev.transfermanager.constants.DirectoryPaths.PROCESS_PATH;


/**
 * Собрали ExcelParser для чтения excel файла. После чтения, данные идут в эррейлист.
 * Вопрос: для чего они идут в эррей лист?
 **/

@Component
public class FileParser {
    public List<FileInfo> readFile(String fileName) {
        try (FileInputStream fis = new FileInputStream(PROCESS_PATH + fileName)) {
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            List<FileInfo> fileData = new ArrayList<FileInfo>();
            DataFormatter dataFormatter = new DataFormatter();
            DateTimeFormatter pattern = DateTimeFormatter.ofPattern("M/dd/yyyy");//добавил
            for (int n = 1; n < sheet.getPhysicalNumberOfRows(); n++) {
                Row row = sheet.getRow(n);
                FileInfo fileInfo = new FileInfo();
                /**
                 * Не используется - выпилить
                 */
                int i = row.getFirstCellNum();
                fileInfo.setName(dataFormatter.formatCellValue(row.getCell(0)));
                fileInfo.setSurname(dataFormatter.formatCellValue(row.getCell(1)));
                fileInfo.setPatronymic(dataFormatter.formatCellValue(row.getCell(2)));
                fileInfo.setGender(dataFormatter.formatCellValue(row.getCell(3)));
                fileInfo.setBirthday(LocalDate.parse(row.getCell(4).getStringCellValue(),pattern));
                fileData.add(fileInfo);
            }
            //скачать файлы с разной генерацией даты. Попробовать в проекте.
            // Заполнен строками Фамилия Имя Отчество Гендер
            return fileData;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

