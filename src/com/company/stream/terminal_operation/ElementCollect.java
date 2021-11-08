package com.company.stream.terminal_operation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ElementCollect {
    public static void main(String[] args) {
        Stream<String> stream1 = Stream.of("넷","둘","하나", "셋");

        List<String> list = stream1.collect(Collectors.toList());
        for( String e : list){
            System.out.print(e + " ");
        }

        System.out.println();
        List<Memebers> members = new ArrayList<>();
        members.add(new Memebers(1, "jjm", "01011112222"));
        members.add(new Memebers(2, "aaa", "01011112222"));
        members.add(new Memebers(3, "bbb", "01011112222"));

        Map<Boolean, List<Memebers>> resultMap1 = members.stream().collect(Collectors.partitioningBy(e->e.getId()>1));
        resultMap1.get(true).forEach(e-> System.out.println(e.getName()));

        Map<String, List<Memebers>> resultMap2= members.stream().collect(
                Collectors.groupingBy(Memebers::getPhone)
        );

        resultMap2.get("01011112222").forEach(e-> System.out.println(e));
    }


}
class Memebers implements Serializable {
    private long id;
    private String name;
    private String phone;

    public Memebers(long id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Memebers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}