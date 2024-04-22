package ru.vasilyev.transfermanager.utils;

import com.opencsv.bean.AbstractBeanField;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * LocalDateCSVConverter позволяет считывать дату с CSV файлов данного образца (строчка 13)
 */
public class LocalDateCSVConverter extends AbstractBeanField {
    @Override
    protected Object convert(String s)  {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return LocalDate.parse(s, formatter);
    }
}
