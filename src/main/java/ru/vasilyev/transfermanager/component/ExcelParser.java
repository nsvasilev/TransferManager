package ru.vasilyev.transfermanager.component;


import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import ru.vasilyev.transfermanager.dto.BankUserDto;
import ru.vasilyev.transfermanager.property.FileSystemWatcherProperties;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ExcelParser implements FileParser {
    private final FileSystemWatcherProperties fileSystemWatcherProperties;

    public ExcelParser(FileSystemWatcherProperties fileSystemWatcherProperties) {
        this.fileSystemWatcherProperties = fileSystemWatcherProperties;
    }

    public List<BankUserDto> readFile(String fileName) {
        try (FileInputStream fis = new FileInputStream(fileSystemWatcherProperties.processPathDirectory() + fileName)) {
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            List<BankUserDto> bankUserDtoList = new ArrayList<>();
            DataFormatter dataFormatter = new DataFormatter();
            DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MM/dd/yyyy");//добавил
            for (int n = 1; n < sheet.getPhysicalNumberOfRows(); n++) {
                Row row = sheet.getRow(n);
                BankUserDto bankUserDto = new BankUserDto();
                bankUserDto.setFirstname(dataFormatter.formatCellValue(row.getCell(0)));
                bankUserDto.setLastname(dataFormatter.formatCellValue(row.getCell(1)));
                bankUserDto.setPatronymic(dataFormatter.formatCellValue(row.getCell(2)));
                bankUserDto.setGender(dataFormatter.formatCellValue(row.getCell(3)));
                bankUserDto.setBirthDate(LocalDate.parse(row.getCell(4).getStringCellValue(), pattern));
                bankUserDto.setBalance(Double.parseDouble(dataFormatter.formatCellValue(row.getCell(5))));
                bankUserDtoList.add(bankUserDto);
            }
            return bankUserDtoList;

        } catch (IOException e) {
            log.info("ошибка при чтении excel файла" + e.getMessage());
            throw new RuntimeException(e);
        }
    }


}

