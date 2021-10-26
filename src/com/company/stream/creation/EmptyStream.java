package com.company.stream.creation;

import java.util.stream.Stream;

public class EmptyStream {
    public static void main(String[] args) {
        Stream<Object> stream = Stream.empty();
        System.out.println(stream.count());
    }
}
