package ru.vasilyev.transfermanager.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FileInfo {
    private String name;
    private String soname;
    private String patronymic;
    private String gender;
    private LocalDate birthday;


    public FileInfo(String name, String soname, String patronymic, String gender, LocalDate birthday) {
        this.name = name;
        this.soname = soname;
        this.patronymic = patronymic;
        this.gender = gender;
        this.birthday = birthday;
    }

    public FileInfo() {

    }


}
