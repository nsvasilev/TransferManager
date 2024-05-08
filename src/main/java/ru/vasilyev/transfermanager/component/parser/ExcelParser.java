package ru.vasilyev.transfermanager.component.parser;


import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import ru.vasilyev.transfermanager.dto.BankUserDto;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * ExcelParser - класс, предназначенный для парсирования excel файлов.
 * создаем list , типизированный классом BankUserDto , принимающий в параметрах файл
 * используем try with resources
 * используем обработку ошибок
 */
@Component
@Slf4j
public class ExcelParser implements FileParser {
    public List<BankUserDto> readFile(File fileName) {
        try (FileInputStream fis = new FileInputStream(fileName)) {
            Workbook workbook = new XSSFWorkbook(fis); // создаем книгу excel, в параметрах наш файл
            Sheet sheet = workbook.getSheetAt(0); //создаем филд sheet, куда возвращаем первую страницу файла
            List<BankUserDto> bankUserDtoList = new ArrayList<>(); //создаем новый лист, куда будут сохранятся все считанные данные
            DataFormatter dataFormatter = new DataFormatter(); //создаем обьект класса dataFormatter
            DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MM/dd/yyyy");//добавил формат для считывания даты.
            for (int n = 1; n < sheet.getPhysicalNumberOfRows(); n++) //пробегаемся по каждому столбцу в одной строчке
            {
                /**
                 * сохраняем последующие данные в ранее созданный лист bankUserDto
                 */
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
            return bankUserDtoList; // возвращаем лист для дальнейшей работы с ним

        } catch (IOException e) {
            log.info("ошибка при чтении excel файла" + e.getMessage());
            throw new RuntimeException(e);
        }
    }


}

