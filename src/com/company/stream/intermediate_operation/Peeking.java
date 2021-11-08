package com.company.stream.intermediate_operation;

import java.util.stream.IntStream;

public class Peeking {
    public static void main(String[] args) {
        IntStream stream = IntStream.of(7,5,5,2,1,2,3,5,4,6);

        stream.peek(s -> System.out.println("원본 스트림: " +s))
                .skip(2)
                .peek(s-> System.out.println("skip(2) 실행 후: "+s))
                .limit(5)
                .peek(s-> System.out.println("limit(5) 실행 후: "+s))
                .sorted()
                .peek(s-> System.out.println("sorted() 실행 후: "+ s))
                .forEach(n-> System.out.println(n));

        //
    }
}
