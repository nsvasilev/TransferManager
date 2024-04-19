package ru.vasilyev.transfermanager.component;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;
import ru.vasilyev.transfermanager.dto.BankUserDto;
import ru.vasilyev.transfermanager.interfaces.FileParser;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static ru.vasilyev.transfermanager.constants.DirectoryPaths.PROCESS_PATH;

@Component
public class CSVParser implements FileParser {
    public List<BankUserDto> readFile(String fileName) {

       //  fileName = PROCESS_PATH + fileName;
        {
            try (var bufferedReader = Files.newBufferedReader(Path.of(PROCESS_PATH + fileName), StandardCharsets.UTF_8)){

                ColumnPositionMappingStrategy<BankUserDto> strategy = new ColumnPositionMappingStrategy<>();
                strategy.setType(BankUserDto.class);
                String[] fields = {"firstname", "lastname", "patronymic","gender", "birtDate","balance"};
                strategy.setColumnMapping(fields);

                CsvToBean<BankUserDto> csvToBean = new CsvToBeanBuilder<BankUserDto>(bufferedReader)
                        .withMappingStrategy(strategy)
                        .withType(BankUserDto.class)
                        .withThrowExceptions(false)
                        .build();

                List<BankUserDto> fileData = csvToBean.parse();
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
