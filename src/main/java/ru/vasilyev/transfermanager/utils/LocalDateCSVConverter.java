package ru.vasilyev.transfermanager.utils;

import com.opencsv.bean.AbstractBeanField;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * LocalDateCSVConverter позволяет считывать дату с CSV файлов данного образца (строчка 13)
 * TODO: AbstractBeanField - подсвечивается жёлтым. Ему нужен даймонт оператор <> с двумя типами. Первый - то что приходит на вход, второй - на выход
 * В сигнатуре метода изменить возвращаемый тип на целевой
 */
public class LocalDateCSVConverter extends AbstractBeanField {
    @Override
    protected LocalDate convert(String s)  {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return LocalDate.parse(s, formatter);
    }
}
