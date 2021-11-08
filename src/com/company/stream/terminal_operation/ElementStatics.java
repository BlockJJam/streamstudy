package com.company.stream.terminal_operation;

import java.util.Comparator;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ElementStatics {
    public static void main(String[] args) {
        IntStream stream1 = IntStream.of(30,70,90,10);
        IntStream stream2 = IntStream.of(30,70,90,10);
        Stream<String> stream3 = Stream.of("3000","700","90","10");

        System.out.println(stream1.count());
        OptionalInt result2= stream2.min();
        System.out.println(result2.getAsInt());

        System.out.println(stream3.min(Comparator.comparing(e-> e.length())).get());

    }
}
