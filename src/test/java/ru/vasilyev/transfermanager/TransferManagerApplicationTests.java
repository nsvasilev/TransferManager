package ru.vasilyev.transfermanager;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class TransferManagerApplicationTests {


    /**
     *
     */
    @Test
    void contextLoads() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "один");
        map.put(2, "два");
        map.put(3, "три");
        map.put(9, "девять");
        map.put(-1, "Нет такого числа");
        System.out.println(map.getOrDefault(-1000, map.get(-1)));
    }

}
