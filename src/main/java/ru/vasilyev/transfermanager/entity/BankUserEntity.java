package ru.vasilyev.transfermanager.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class BankUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstname;
    private String lastname;
    private String patronymic;
    private String gender;
    private LocalDate birthDate;

    public BankUserEntity(String firstname, String lastname, String patronymic, String gender, LocalDate birthDate) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.patronymic = patronymic;
        this.gender = gender;
        this.birthDate = birthDate;
    }
}


