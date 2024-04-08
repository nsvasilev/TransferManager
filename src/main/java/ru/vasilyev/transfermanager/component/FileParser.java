package ru.vasilyev.transfermanager.component;


import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ru.vasilyev.transfermanager.constants.DirectoryPaths.DIRECTORY_PATH;


/**
 * Собрали ExcelParser для чтения excel файла. Нужно прописать метод, который будет считывать первую строку,
 * после чего перекидывать файл в Error или в Success.
 **/
//на вход файл, на выход содержимое
//создать сервис, в который передается  каждый файл, который видит ватч сервис
//, а он уже занимается распределением
@Component
public class FileParser {


    public List<String> readFile(String fileName){
        try (FileInputStream fis = new FileInputStream(DIRECTORY_PATH + fileName)){




        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // Заполнен строками Фамилия Имя Отчество Гендер
        return new ArrayList<>();
    }

//    public void readFile() {
//
//
//        try (FileInputStream fis = new FileInputStream(DIRECTORY_PATH + "DataList.xlsx");
//             Workbook workbook = new XSSFWorkbook(fis)) {
//            Sheet sheet = workbook.getSheetAt(0);
//
//
//            for (Row row : sheet) //изменили while на for
//            {
//                Iterator<Cell> cellIterator = row.cellIterator();
//
//                while (cellIterator.hasNext()) {
//                    Cell cell = cellIterator.next();
//                    // Обработка различных типов данных в ячейках
//                    System.out.print(cell.toString() + " ");
//                }
//                System.out.println();  // Начало новой строки
//
//            }
//
//        }
//
//
//    }

    //создали метод, переписать, хуйня какая-то пока что.
//    public void checkedStructure() throws FileNotFoundException {
//        FileInputStream fis = new FileInputStream(DIRECTORY_PATH + fileName);
//        int cellsNum;
//        try (Workbook workbook = new XSSFWorkbook(fis)) {
//            cellsNum = workbook.getSheetAt(0).getRow(0).getPhysicalNumberOfCells();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        if (cellsNum == 5) {
//            System.out.println("файл подходит по структуре, перенаправляю в success");
//
//        } else {
//            System.out.println("файл не подходит по структуре. Перенаправляю в error");
//        }
//    }
}

