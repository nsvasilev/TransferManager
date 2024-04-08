package ru.vasilyev.transfermanager.component;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
//на вход файл, на выход содержимое
    //создать сервис, в который передается  каждый файл, который видит ватч сервис
//, а он уже занимается распределением
public class ExelParser {

    public static void main(String[] args) throws IOException {



//            try (FileInputStream fis = new FileInputStream(DIRECTORY_PATH + "DataList.xlsx");
//                 Workbook workbook = new XSSFWorkbook(fis)) {
//                Sheet sheet = workbook.getSheetAt(0);
//
//
//                for (Row row : sheet) //изменили while на for
//                    {
//                    Iterator<Cell> cellIterator = row.cellIterator();
//
//                    while (cellIterator.hasNext()) {
//                        Cell cell = cellIterator.next();
//                        // Обработка различных типов данных в ячейках
//                        System.out.print(cell.toString() + " ");
//                    }
//                    System.out.println();  // Начало новой строки
//
//                }
//
//            }
//
//
//        }
//        //создали метод, переписать, хуйня какая-то пока что.
//        public static void checkedStructure() throws FileNotFoundException {
//            FileInputStream fis = new FileInputStream(DIRECTORY_PATH + fileName);
//            int cellsNum;
//            try (Workbook workbook = new XSSFWorkbook(fis)) {
//                cellsNum = workbook.getSheetAt(0).getRow(0).getPhysicalNumberOfCells();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            if (cellsNum == 5) {
//                System.out.println("файл подходит по структуре, перенаправляю в success");
//
//            }
//            else {
//                System.out.println("файл не подходит по структуре. Перенаправляю в error");
//            }
        }
    }

