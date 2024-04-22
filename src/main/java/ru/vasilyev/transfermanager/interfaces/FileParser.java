package ru.vasilyev.transfermanager.interfaces;

import ru.vasilyev.transfermanager.dto.BankUserDto;

import java.util.List;

/**
 * Вынесли метод
 *
 */
public interface FileParser {

    List<BankUserDto> readFile(String fileName);
}
