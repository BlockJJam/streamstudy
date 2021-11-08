### 스트림의 최종 연산

- 중개 연산을 통해 변환된 스트림은 마지막 최종연산을 통해 각 요소를 소모한 뒤, 결과를 표시합니다
- 정확히는 지연되었던 모든 중개 연산들 → 최종 연산 시에 모두 수행되는 것
- 모든 요소를 소모한 스트림은 더이상 사용 불가!
- 대표적인 최종 연산과 그에 따른 메서드 목록
  1. 요소의 출력: forEach()
  2. 요소의 소모: reduce()
  3. 요소의 검색: findFirst(), findAny()
  4. 요소의 검사: anyMatch(), allMatch(), noneMatch()
  5. 요소의 통계: count(), min(), max()
  6. 요소의 연산: sum(), average()
  7. 요소의 수집: collect()


---

### Stream 요소의 출력

- 설명
  - forEach() - 스트림의 각 요소를 소모하여 명시된 동작을 수행
  - void를 return하므로, 모든 요소를 출력하는 용도로 주로 사용
- 예제

```java
public class ElementPrint {
    public static void main(String[] args) {
        Stream<String> strStream = Stream.of("넷","둘","셋","하나");
        strStream.forEach(System.out::println);
    }
}
```

- 결과

```
넷
둘
셋
하나
```

---

### Stream 요소의 소모

- 설명
  - 최종 연산은 모두 스트림의 각 요소를 소모하여 연산을 수행하는 것이 기본 메커니즘
  - reduce() - 하지만, 이 메서드는 1번째와 2번째 요소를 가지고 연산 후, 그 결과와 3번째 요소를 가지고 또 다시 연산하면서 최종 요소까지 연산을 수행 후 결과를 반환
    - 추가적으로, 초깃값(initValue)을 전달하면, 초깃값과 첫번째요소 연산부터가 시작이 된다
- 예제

```java
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
```

- 결과

```
넷++둘++하나++셋

result2 = 시작--넷--둘--하나--셋
```

: result1이 Optional<String> 을 반환하는 이유는 결과가 null일 가능성이 있기 때문

: result2가 String으로 바로 반환이 가능한 이유는 "초깃값"이 있기 때문

---

### Stream 요소의 검색

- 설명
  - findFirst(), findAny(): 해당 스트림에서 첫번째 요소를 참조하는 Optional객체를 반환
    - 두 메소드 모두 비어있는 스트림에서는 Null객체가 담긴 Optional 객체를 반환
- 예제

```java
public class ElementSearch {
    public static void main(String[] args) {
        IntStream stream1 = IntStream.of(4,2,7,1,3,6);
        IntStream stream2 = IntStream.of(4,2,7,1,3,6);

        OptionalInt result1 = stream1.sorted().findFirst();
        System.out.println("result1 = " + result1.getAsInt());

        OptionalInt result2 = stream2.sorted().findAny();
        System.out.println("result2 = " + result2.getAsInt());

    }
}
```

- 결과

```
result1 = 1
result2 = 1
```

: 두 메서드(`findAny()`, `findFirst()`) 모두 동일한 결과를 출력
: 병렬 스트림에서는 예외! → `findAny()`를 사용해야만 정확한 연산 결과를 반환



---

### Stream 요소의 검사

- 설명
  - 해당 스트림 요소 중에 특정 조건을 만족하는 요소가 있는지, 모두 만족하거나 모두 족하지 않는 경우인지를 다음 메서드로 확인가능
  - anyMatch(): 해당 스트림 일부 요소가 특정 조건을 만족할 때, true(Boolean) return
  - allMatch(): 해당 스트림의 모든 요소가 특정 조건을 만족할 때, true를 반환
  - noneMatch(): 해당 스트림의 모든 요소가 특정 조건을 만족하지 않을 때, true를 반환
- 예제

```java
public class ElementMatch {
    public static void main(String[] args) {
        IntStream stream1 = IntStream.of(30, 90,70,10);
        IntStream stream2 = IntStream.of(30, 90,70,10);
        IntStream stream3 = IntStream.of(30, 90,70,10);

        System.out.println(stream1.anyMatch(n -> n > 80));
        System.out.println(stream2.allMatch(n -> n> 80));
        System.out.println(stream3.noneMatch(n -> n > 100));
    }
}
```

- 결과

```
true
false
true
```

---

### Stream의 요소 통계

- 설명
  - count(): 해당 스트림 요소의 총 갯수를 long 타입으로 반환
  - max(): 해당 스트림의 요소 중에서 가장 큰 값
  - min(): 해당 스트림의 요소 중에서 가장 작은 값을 참조하는 Optional 객체를 반환
- 예제

```java
public class ElementStatics {
    public static void main(String[] args) {
        IntStream stream1 = IntStream.of(30,70,90,10);
        IntStream stream2 = IntStream.of(30,70,90,10);
        Stream<String> stream3 = Stream.of("3000","700","90","10");

        System.out.println(stream1.count());
        OptionalInt result2= stream2.min();
        System.out.println(result2.getAsInt());

        System.out.println(stream3.min(Comparator.comparing(e-> e.length())).get());

    }
}
```

- 결과

```
4
10
90
```

: stream3의 경우 문자열이기 때문에, Comparator(비교객체)를 활용해야 연산이 가능하다

---

### Stream의 요소 연산

- 설명
  - IntStream이나 DoubleStream과 같은 기본타입 스트림에는 모든 요소에 대한 sum과 average를 구할 수 있는 메서드를 제공 → sum(), average()
  - average(): 각 기본타입으로 Wrapping된 Optional객체를 반환
- 예제

```java
public class ElementOperation {
    public static void main(String[] args) {
        IntStream stream1 = IntStream.of(30,90,70,10);
        DoubleStream stream2 = DoubleStream.of(30.3, 90.9, 70.7, 10.1);

        System.out.println(stream1.sum());
        System.out.println(stream2.average().getAsDouble());// average() -> OptionalDouble
    }
}
```

- 결과

```
200
50.5
```

---

### Stream의 요소 수집

- 설명
  - `collect()`: 인수로 전달되는 `Collectors` 객체에 구현된 방식대로 요소를 수집
    - Collectors 클래스에는 미리 정의된 다양한 방법이 클래스 메서드로 정의
  - 사실 `Collectors` 메서드를 더 유의있게 봐야한다
    1. Stream을 배열이나 컬렉션으로 반환
       : `toArray(), toCollection(), toList(), toMap()`
    2. 요소의 통계와 연산메서드와 같은 동작을 수행
       : `counting(), maxBy(), minBy(), summingInt(), averagingInt() ...`
    3. 요소의 소모와 같은 동작을 수행
       : `reducing(), joining()`
    4. 요소의 그룹화와 분할
       : `groupingBy(), partitioningBy()` → 정확한 활용법을 알도록!
- 예제

```java
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

// 여기 밑은 재료
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
```

- 결과

```java
넷 둘 하나 셋 
aaa
bbb
Memebers{id=1, name='jjm', phone='01011112222'}
Memebers{id=2, name='aaa', phone='01011112222'}
Memebers{id=3, name='bbb', phone='01011112222'}
```

: partitioningBy()나 groupingBy()를 활용하게 되면, 요소 별 특징을 가지고 그룹화를 하거나, 분할을 할 수 있다
→ 다만 핸들링하기 쉽지 않으므로, Collectors와 Fnctional 인터페이스에 대해 더 공부하고 사용할 것을 추천!