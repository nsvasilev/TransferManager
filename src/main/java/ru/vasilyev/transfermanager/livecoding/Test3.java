package ru.vasilyev.transfermanager.livecoding;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test3 {
    public static void main(String[] args) {
        String targetMany = "abbcccdddd";
        System.out.println(countChars(targetMany));
    }

    //3. Посчитать количество вхождений символов в строку используя стримы.
    //{
    //a:4,
    //s:3,
    //d:2,
    //f:2
    //}
    public static Map<String, Long> countChars(String randomChars) {
        Map<String, Long> collect = randomChars.chars().mapToObj(Character::toChars)
                .collect(Collectors.groupingBy(String::valueOf, Collectors.counting()));

        String collect1 = collect.entrySet().stream()
                .map(entry -> "\"" + entry.getKey() + "\"" + " : " + entry.getValue())
                .collect(Collectors.joining("\r\n", "{\n", "\n}"));
        System.out.println(collect1);


        return collect;




    }
}
