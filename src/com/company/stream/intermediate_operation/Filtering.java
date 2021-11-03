package com.company.stream.intermediate_operation;

import java.util.stream.IntStream;

public class Filtering {
    public static void main(String[] args) {
        IntStream stream1 = IntStream.of(7,5,5,2,1,2,3,5,4,6);
        IntStream stream2 = IntStream.of(7,5,5,2,1,2,3,5,4,6);

        stream1.distinct().forEach(e-> System.out.print(e+" "));
        System.out.println();

        stream2.filter(n-> n % 2 != 0 ).forEach(e-> System.out.print(e+ " "));
        // filter메서드의 파라미터 타입 Predicate는 람다(혹은 메서드참조)를 통해 Boolean연산이 가능하도록 한다
    }
}
