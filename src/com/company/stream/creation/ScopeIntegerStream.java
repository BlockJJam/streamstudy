package com.company.stream.creation;

import java.util.stream.IntStream;

public class ScopeIntegerStream {
    public static void main(String[] args) {
        IntStream stream1 = IntStream.range(1,4);
        stream1.forEach(e-> System.out.print(e+" "));

        System.out.println();

        IntStream stream2 = IntStream.rangeClosed(1,4);
        stream2.forEach(e-> System.out.print(e+" "));
    }
}
