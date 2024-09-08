package ru.vasilyev.transfermanager.livecoding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test7 {
    public static void main(String[] args) {
        //6
//  Напишите функцию, которая принимает на вход массив состоящий из последовательности чисел,
//  первое из которых является количеством последующих элементов, которые нужно поместить в массив,
//  а за ней следуют элементы этого массива, и возвращающая отдельные массивы.
//  Например: 3, 4, 0, 2, 1, 2, 2, 4, 5 будет превращено в [4, 0, 2], [2], [4, 5]
//
        int arr[] = new int[]{4, 4, 0, 2, 1, 3, 2, 4, 5};
        System.out.println(returnArrays(arr));
    }

    public static List<List<Integer>> returnArrays(int arr[]) {
        List<List<Integer>> arrayList1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list2.add(arr[i]);
        } // создали лист из массива, заполнили элементами
        while (!list2.isEmpty() && list2.get(0) < list2.size()) {
                List<Integer> list = new ArrayList<>();
                for (int j = 1; j < list2.get(0)+1; j++) {
                    list.add(list2.get(j));
                }
                arrayList1.add(list);
                int a = list2.get(0);
            for (int e = 0; e < a+1; e++) {
                list2.remove(0);
            }
        }
        return arrayList1;
    }
}
