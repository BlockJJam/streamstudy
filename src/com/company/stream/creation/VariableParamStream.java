package com.company.stream.creation;

import java.util.stream.Stream;

public class VariableParamStream {
    public static void main(String[] args) {
        Stream<Double> stream = Stream.of(4.2, 2.5, 3.1, 1.9);
        stream.forEach(System.out::println);
    }
}
