package com.company.stream.creation;

import java.util.Random;
import java.util.stream.IntStream;

public class SpecTypeRandomsStream {
    public static void main(String[] args) {
        IntStream stream = new Random().ints(4);
        stream.forEach(System.out::println);
    }
}
