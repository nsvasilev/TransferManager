package ru.vasilyev.transfermanager.livecoding;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        final List<String> list = Stream.of("b", "a", "d", "c")
                .map(val -> val + "t")
                .collect(Collectors.toList());
        list.add("w");
        System.out.println(list.size());

        System.out.println("-----------------");
    }

}
