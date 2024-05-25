package ru.vasilyev.transfermanager.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FailedFileDto {
   private String pathOfFile;
   private LocalDate dateOfAppearance;
   private long size;
}
