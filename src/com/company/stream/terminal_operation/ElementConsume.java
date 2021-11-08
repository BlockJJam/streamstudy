package com.company.stream.terminal_operation;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.stream.Stream;

public class ElementConsume {
    public static void main(String[] args) {
        Stream<String> stream1 = Stream.of("넷", "둘", "하나", "셋");
        Stream<String> stream2 = Stream.of("넷", "둘", "하나", "셋");

        Optional<String> result1 = stream1.reduce((s1,s2)-> s1 +"++"+s2);
        result1.ifPresent(System.out::println);
        System.out.println();

        String result2 = stream2.reduce("시작",(s1,s2)-> s1+"--"+s2);
        System.out.println("result2 = " + result2);
    }
}
