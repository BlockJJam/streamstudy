package com.company.stream.creation;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaStream {
    public static void main(String[] args) {
        Stream stream = Stream.iterate(2, n-> n+2);
    }
}
