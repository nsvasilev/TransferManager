package ru.vasilyev.transfermanager.livecoding;

import java.util.List;

public class Test4 {
    public static void main(String[] args) {

        List<Integer> integers = List.of(3, 2, 1);
        integers.stream()
                .peek(System.out::println);

        System.out.println("---------------------");

        integers.stream()
                .peek(System.out::println)
                .forEachOrdered(System.out::println);
        System.out.println("---------------------");

        integers.stream()
                .peek(System.out::println)
                .sorted()
                .forEach(System.out::println);
    }
}
