package ru.vasilyev.transfermanager.component;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import ru.vasilyev.transfermanager.dto.BankUserDto;
import ru.vasilyev.transfermanager.interfaces.FileParser;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static ru.vasilyev.transfermanager.constants.DirectoryPaths.PROCESS_PATH;

@Component
public class ExcelParser implements FileParser {


    public List<BankUserDto> readFile(String fileName) {
        try (FileInputStream fis = new FileInputStream(PROCESS_PATH + fileName)) {
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            List<BankUserDto> fileData = new ArrayList<BankUserDto>();
            DataFormatter dataFormatter = new DataFormatter();
            DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MM/dd/yyyy");//добавил
            for (int n = 1; n < sheet.getPhysicalNumberOfRows(); n++) {
                Row row = sheet.getRow(n);
                BankUserDto fileInfo = new BankUserDto();
                int i = row.getFirstCellNum();
                /**
                 * ЧТО ТАКОЕ fileInfo и dataFOrmatter????
                 * ДТО у нас называется BankUserDto
                 * TODO:
                 */
                fileInfo.setFirstname(dataFormatter.formatCellValue(row.getCell(0)));
                fileInfo.setLastname(dataFormatter.formatCellValue(row.getCell(1)));
                fileInfo.setPatronymic(dataFormatter.formatCellValue(row.getCell(2)));
                fileInfo.setGender(dataFormatter.formatCellValue(row.getCell(3)));
                //fileInfo.setBirthDate(LocalDate.parse(dataFormatter.formatCellValue(row.getCell(4))));
                fileInfo.setBirthDate(LocalDate.parse(row.getCell(4).getStringCellValue(),pattern));
                fileInfo.setBalance(row.getCell(5).getNumericCellValue());
                fileData.add(fileInfo);
            }
            //читает дату только определенного образца
            return fileData;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

