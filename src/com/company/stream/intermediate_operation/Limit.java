package com.company.stream.intermediate_operation;

import java.util.stream.IntStream;

public class Limit {
    public static void main(String[] args) {
        IntStream stream1 = IntStream.range(0,10);
        IntStream stream2 = IntStream.range(0,10);
        IntStream stream3 = IntStream.range(0,10);
        IntStream stream4 = IntStream.range(0,10);
        IntStream stream5 = IntStream.range(0,10);

        stream1.skip(4).forEach(n-> System.out.print(n+" "));
        System.out.println();

        stream2.limit(4).forEach(n -> System.out.print(n+" "));
        System.out.println();

        stream3.skip(3).limit(5).forEach(n -> System.out.print(n + " "));
        System.out.println();

        // param check
//        stream4.skip(4).limit(-1).forEach(n -> System.out.print(n + " ")); 마이너스는 예외를 던짐: IllegalArgumentException
        stream4.skip(4).limit(11).forEach(n -> System.out.print(n + " "));
        System.out.println();

        stream5.skip(11).forEach(n-> System.out.print(n+ " "));
        System.out.println();
    }

}
