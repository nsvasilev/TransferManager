package ru.vasilyev.transfermanager.component;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.stereotype.Component;
import ru.vasilyev.transfermanager.dto.BankUserDto;
import ru.vasilyev.transfermanager.interfaces.FileParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ru.vasilyev.transfermanager.constants.DirectoryPaths.PROCESS_PATH;

@Component
public class CSVParser implements FileParser {
    public List<BankUserDto> readFile(String fileName) throws IOException {

         fileName = PROCESS_PATH + fileName;
        {
            try {
                FileReader filereader = new FileReader(fileName);
                List<BankUserDto> fileData = new ArrayList<BankUserDto>();
                CSVReader csvReader = new CSVReaderBuilder(filereader)
                        .withSkipLines(1)
                        .build();
                List<String[]> allData = csvReader.readAll();
                for (String[] row : allData) {
                    for (String cell : row) {
                        System.out.print(cell + "\t");
                    }
                    System.out.println();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}








        /**
        * Снизу отдельный поток для чтения файла.
         **/
//        public static void main(String[] args) throws IOException {
//
//// Java code to illustrate reading a
//// all data at once
//            String fileName = PROCESS_PATH + "convertcsv.csv";
//            {
//                try {
//                    FileReader filereader = new FileReader(fileName);
//                    CSVReader csvReader = new CSVReaderBuilder(filereader)
//                            .withSkipLines(1)
//                            .build();
//                    List<String[]> allData = csvReader.readAll();
//                    for (String[] row : allData) {
//                        for (String cell : row) {
//                            System.out.print(cell + "\t");
//                        }
//                        System.out.println();
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
