package ru.vasilyev.transfermanager.livecoding;

import jakarta.validation.constraints.AssertTrue;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test21 {


    public static void main(String[] args) {
        //1.Определить количество чисел кратных 33 в последовательности от 0 до 1_000_000
//        long count = Stream.iterate(0, n -> n + 1).limit(1_000_000).filter(val -> val % 33 == 0).count();
//        System.out.println(count);

//
//        IntStream.rangeClosed(0, 1_000_000).filter(val -> val % 33 == 0).count();

        //2.Сгруппировать чилса от 0 до 10 по признаку четности в Map<Boolean, List<Integer>>

//       Map<Boolean, List<Integer>> collect = IntStream.rangeClosed(0, 10).boxed().collect(Collectors.partitioningBy(value -> value % 2 == 0));
//        System.out.println(collect);

//        Map<Boolean, List<Integer>> collect1 = IntStream.rangeClosed(0, 10)
//                .boxed()
//                .collect(Collectors.groupingBy(value -> value % 2 == 0));
//        System.out.println(collect1);

        //  3.Вывести все числа Фибоначчи до 15 элемента

//        Stream.iterate(new int[]{0, 1}, n -> new int[]{n[1], n[0] + n[1]}).limit(15).map(f -> f[0]).forEach(System.out::println);
//
//        Stream<String> stringStream = Stream.of("a", "b", "c").filter(element -> element.contains("b"));
//        System.out.println(stringStream.findAny()); //b
//        System.out.println(stringStream.findFirst()); //b

        //вернуть список user у которых группа начинается на "x"

        User user = new User();
        User user1 = new User();

        List<User> user2 = List.of(user, user1);

//        user2.stream()
//                .filter(user3 -> user3.getGroups().stream()
//                        .anyMatch(groups -> groups.getName().startsWith("x"))).toList();


        List <String> list = Arrays.asList("A", "B", "C", "Q", "W", "F");
        Optional <String> result = list.stream().filter(w -> w.contains("C")).findAny();
        System.out.println(result);
    }

    @Data
    static class User {

        private String name;
        private Integer age;
        private List<Groups> groups;

    }

    @Data
    static class Groups {

        private String name;
        private String description;
    }




}
