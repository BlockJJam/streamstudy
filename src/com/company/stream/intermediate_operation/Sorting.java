package com.company.stream.intermediate_operation;

import org.w3c.dom.ls.LSOutput;

import java.util.Comparator;
import java.util.stream.Stream;

public class Sorting {
    public static void main(String[] args) {
        Stream<String> stream1 = Stream.of("JAVA","HTML","JAVASCRIPT","CSSSSSS");
        Stream<String> stream2 = Stream.of("JAVA","HTML","JAVASCRIPT","CSS","PYTHON");
        Stream<String> stream3 = Stream.of("JAVA","HTML","JAVASCRIPT","CSS","PYTHON");
        Stream<String> stream4 = Stream.of("JAVA","HTML","JAVASCRIPT","CSS","PYTHON");
        Stream<String> stream5 = Stream.of("JAVA","HTML","JAVASCRIPT","CSS",null,"PYTHON");
        Stream<String> stream6 = Stream.of("JAVA","HTML","JAVASCRIPT","CSS",null,"PYTHON");

        stream1.sorted().forEach(s -> System.out.print(s + " "));
        System.out.println();

        stream2.sorted(Comparator.comparing(e-> e.length())).forEach(s -> System.out.print(s +" "));
        System.out.println();
        stream3.sorted(Comparator.reverseOrder()).forEach(s-> System.out.print(s+ " "));
        System.out.println();
        stream4.sorted(Comparator.naturalOrder()).forEach(s-> System.out.print(s+ " "));
        System.out.println();
        stream5.sorted(Comparator.nullsFirst(Comparator.reverseOrder())).forEach(s-> System.out.print(s+ " "));
        System.out.println();
        stream6.sorted(Comparator.nullsLast(Comparator.naturalOrder())).forEach(s-> System.out.print(s+ " "));

    }
}
