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
@Table(name= "bank_user")
public class BankUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String patronymic;
    private String gender;
    @Column(name = "birth_date")
    private LocalDate birthDate;

    public BankUserEntity(String firstname, String lastname, String patronymic, String gender, LocalDate birthDate) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.patronymic = patronymic;
        this.gender = gender;
        this.birthDate = birthDate;
    }
}


