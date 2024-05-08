package ru.vasilyev.transfermanager.component.parser;

import ru.vasilyev.transfermanager.dto.BankUserDto;

import java.io.File;
import java.util.List;

/**
 * Вынесли метод
 * TODO: Интерфейсы лучше хранить рядом с классами, где их имплементируют
 */
public interface FileParser {

    List<BankUserDto> readFile(File fileName);
}
