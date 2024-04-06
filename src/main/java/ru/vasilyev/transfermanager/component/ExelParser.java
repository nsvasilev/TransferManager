package ru.vasilyev.transfermanager.component;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import static ru.vasilyev.transfermanager.constants.ProjectConstants.DIRECTORY_PATH;


/**
*Собрали ExcelParser для чтения excel файла. Нужно прописать метод, который будет считывать первую строку,
 * после чего перекидывать файл в Error или в Success.
 *
*
*
 **/

public class ExelParser {
    public static void main(String[] args) throws IOException {



            try (FileInputStream fis = new FileInputStream(DIRECTORY_PATH + "DataList.xlsx");
                 Workbook workbook = new XSSFWorkbook(fis)) {
                Sheet sheet = workbook.getSheetAt(0);
                Iterator<Row> rowIterator = sheet.iterator();

                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    Iterator<Cell> cellIterator = row.cellIterator();

                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        // Обработка различных типов данных в ячейках
                        System.out.print(cell.toString() + " ");
                    }
                    System.out.println();  // Начало новой строки
                }
            }
        }
    }

