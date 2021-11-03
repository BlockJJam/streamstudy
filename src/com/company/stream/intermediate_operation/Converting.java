package com.company.stream.intermediate_operation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Converting {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("HTML","CSS","JAVA");
        stream.map(s -> s.length()).forEach(System.out::println);

        List<List<String>> listStream = Arrays.asList(Arrays.asList("12","123","1234"), Arrays.asList("12","123","1234"), Arrays.asList("12","123","1234"));
        List<Integer> result = listStream.stream()
                .flatMap(Collection::stream)
                .filter(str -> str.length()>3)
                .map(filtered-> Integer.parseInt(filtered))
                .collect(Collectors.toList());

        System.out.println("result = " + result);
    }
}
