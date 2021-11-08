package com.company.stream.terminal_operation;

import java.util.stream.Stream;

public class ElementPrint {
    public static void main(String[] args) {
        Stream<String> strStream = Stream.of("넷","둘","셋","하나");
        strStream.forEach(System.out::println);
    }
}
