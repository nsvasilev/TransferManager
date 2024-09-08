package ru.vasilyev.transfermanager.livecoding;

import java.util.Arrays;

public class Test6 {
    public static void main(String[] args) {
        //6
        //Вывести только нечетные элементы массива
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        Arrays.stream(arr)
                .filter(i -> i % 2 == 0)
                .forEach(i -> System.out.print(i + " "));


    }
}
