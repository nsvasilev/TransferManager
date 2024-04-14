package ru.vasilyev.transfermanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileInfo {
    private String name;
    private String surname;
    private String patronymic;
    private String gender;
    private LocalDate birthday;
}
