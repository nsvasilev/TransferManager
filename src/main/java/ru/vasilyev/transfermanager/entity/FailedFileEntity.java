package ru.vasilyev.transfermanager.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


/**
 * В зибернейте есть соглашение, слова разделяются '_' и начинаются с маленькой буквы.
 * Соответсвенно, в базе заранее нужно создать таблицу, которая соответствует этому соглашению
 *
 * Мы договорились переименовать в
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
public class FailedFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Column(name = "path_of_file")
    private String pathOfFile;
//    @Column(name = "date_of_appearance")
    private LocalDateTime dateOfAppearance;
    private long size;


    public FailedFileEntity(Long id, String pathOfFile, LocalDateTime dateOfAppearance, long size) {
        this.id = id;
        this.pathOfFile = pathOfFile;
        this.dateOfAppearance = dateOfAppearance;
        this.size = size;
    }
}
