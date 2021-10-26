package com.company.stream.creation;

import java.util.ArrayList;
import java.util.stream.Stream;

public class CollectionStream {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();

        list.add(4);
        list.add(3);
        list.add(2);
        list.add(1);

        Stream<Integer> stream = list.stream();
        stream.forEach(System.out::println);
    }
}
