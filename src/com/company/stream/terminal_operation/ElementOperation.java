package com.company.stream.terminal_operation;

import java.util.OptionalDouble;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class ElementOperation {
    public static void main(String[] args) {
        IntStream stream1 = IntStream.of(30,90,70,10);
        DoubleStream stream2 = DoubleStream.of(30.3, 90.9, 70.7, 10.1);

        System.out.println(stream1.sum());
        System.out.println(stream2.average().getAsDouble());// average() -> OptionalDouble
    }
}
