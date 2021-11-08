package com.company.stream.terminal_operation;

import java.util.OptionalInt;
import java.util.stream.IntStream;

public class ElementSearch {
    public static void main(String[] args) {
        IntStream stream1 = IntStream.of(4,2,7,1,3,6);
        IntStream stream2 = IntStream.of(4,2,7,1,3,6);

        OptionalInt result1 = stream1.sorted().findFirst();
        System.out.println("result1 = " + result1.getAsInt());

        OptionalInt result2 = stream2.sorted().findAny();
        System.out.println("result2 = " + result2.getAsInt());

    }
}
