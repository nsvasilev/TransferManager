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
@Table(name= "error_file")
public class UnexpectedExtensionFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "pathoffile")
    private String pathOfFile;
    @Column(name = "dateofappearance")
    private LocalDate dateOfAppearance;
    private long size;


    public UnexpectedExtensionFileEntity(Long id, String pathOfFile, LocalDate dateOfAppearance, long size) {
        this.id = id;
        this.pathOfFile = pathOfFile;
        this.dateOfAppearance = dateOfAppearance;
        this.size = size;
    }
}
