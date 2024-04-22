package ru.vasilyev.transfermanager.dto;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vasilyev.transfermanager.utils.LocalDateCSVConverter;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankUserDto {
    @CsvBindByPosition(position = 0)
    private String firstname;
    @CsvBindByPosition(position = 1)
    private String lastname;
    @CsvBindByPosition(position = 2)
    private String patronymic;
    @CsvBindByPosition(position = 3)
    private String gender;
    @CsvBindByPosition(position = 4)
    @CsvCustomBindByPosition(position = 4,converter = LocalDateCSVConverter.class)
    private LocalDate birthDate;
    @CsvBindByPosition(position = 5)
    private double balance;
}
