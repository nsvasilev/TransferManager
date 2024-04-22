package ru.vasilyev.transfermanager.interfaces;

import ru.vasilyev.transfermanager.dto.BankUserDto;

import java.util.List;

/**
 * Вынесли метод
 * TODO: Интерфейсы лучше хранить рядом с классами, где их имплементируют
 */
public interface FileParser {

    List<BankUserDto> readFile(String fileName);
}
