package ru.vasilyev.transfermanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankUserDto {
    private String firstname;
    private String lastname;
    private String patronymic;
    private String gender;
    private LocalDate birthDate;
    private double balance;
}
