package ru.vasilyev.transfermanager.livecoding;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test5 {
    public static void main(String[] args) {
        //5       Дан массив ints. Что появится в консоли?
        int[] values = new int[]{1, 4, 9, 16};
        IntStream stream = IntStream.of(values);
        stream.forEach(System.out::println);
    }
}
