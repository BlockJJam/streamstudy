### 스트림의 파이프라인이 끝나지 않는 이유?

- 스트림의 중개 연산이 가능한 이유이기도 한데, 중개 연산을 통해 스트림이 다른 스트림으로 변환
  → 중개 연산으로 연결해서 연속 사용이 가능
- filter - map기반의 API를 사용함으로써, 지연(lazy) 연산이 가능해짐
- 대표적인 중개 연산
    1. 스트림 필터링: filter(), distinct()
    2. 스트림 변환: map(), flatMap()
    3. 스트림 제한: limit(), skip()
    4. 스트림 정렬: sorted()
    5. 스트림 연산 결과 확인: peek()


---

### Stream 필터링

- 설명
    - filter() 메서드는 조건(predicate)에 맞는 요소만으로 구성된 새로운 스트림을 반환하며, distinct() 메서드는 중복된 요소가 제거된 새로운 스트림을 반환
    - distinct()의 중복을 체크하는 방법 → Object 클래스의 equals() 메서드를 사용
    - filter()메서드의 파라미터 타입 Predicate는 람다(혹은 메서드참조)를 통해 Boolean연산이 가능하도록 한다
- 예제

```java
public class Filtering {
    public static void main(String[] args) {
        IntStream stream1 = IntStream.of(7,5,5,2,1,2,3,5,4,6);
        IntStream stream2 = IntStream.of(7,5,5,2,1,2,3,5,4,6);

        stream1.distinct().forEach(e-> System.out.print(e+" "));
        System.out.println();

        stream2.filter(n-> n % 2 != 0 ).forEach(e-> System.out.print(e+ " "));
    }
}
```

---

### Stream 변환

- 설명
    - 대표적으로 map() 메서드 계열이 있는데, 스트림의 요소들을 → 주어진 함수의 인수로 전달
    - 반환값 = 반환값들로 이루어진 새로운 스트림
    - Stream<List<String>> 처럼, 스트림의 요소가 배열이라면?
      flatMap()을 사용해서 각 배열안의 각 요소의 반환값을 하나로 합친 새로운 스트림을 만들 수 있다

- 예제

```java
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
```

---

### Stream 제한

- 설명
    - limit() 메서드는 해당 스트림의 첫번째 요소부터 전달된 개수(=count)만큼의 요소를 가져와 새로운 스트림을 반환
      → Param은 maxSize를 넘지 말라고 되어있지만, 넘으면 마지막 요소까지만 포함함 (에러로 예외를 던지지 않음)
      → `-1` 과 같은 음수는 에러를 던짐
    - skip() 메서드는 해당 스트림의 첫번째 요소부터 전달된 개수(=count)만큼의 요소를 제외하고 나머지 요소를 가져와 스트림으로 변환
      → Param은 maxSize를 넘지 말라고 되어있지만, 넘으면 마지막 요소까지만 포함함 (에러로 예외를 던지지 않음)
      → `-1` 과 같은 음수는 에러를 던짐
    - limit() 메서드와 skip()메서드를 활용하면, 원하는 범위안의 요소들을 가져올 수도 있음

- 예제

```java
public class Limit {
    public static void main(String[] args) {
        IntStream stream1 = IntStream.range(0,10);
        IntStream stream2 = IntStream.range(0,10);
        IntStream stream3 = IntStream.range(0,10);
        IntStream stream4 = IntStream.range(0,10);
        IntStream stream5 = IntStream.range(0,10);

        stream1.skip(4).forEach(n-> System.out.print(n+" "));
        System.out.println();

        stream2.limit(4).forEach(n -> System.out.print(n+" "));
        System.out.println();

        stream3.skip(3).limit(5).forEach(n -> System.out.print(n + " "));
        System.out.println();

        // param check
//        stream4.skip(4).limit(-1).forEach(n -> System.out.print(n + " ")); 마이너스는 예외를 던짐: IllegalArgumentException
        stream4.skip(4).limit(11).forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        stream5.skip(11).forEach(n-> System.out.print(n+ " "));
        System.out.println();
    }

}
```

---

### Stream 정렬

- 설명
    - sorted() 메서드는 해당 스트림을 주어진 비교자를 이용하여 정렬
      → 비교자를 전달하지 않으면? 사전 편찬 순(= natural order)로 정렬
    - sorted()에 전달되는 Param 타입인 Comparator 메서드 종류
        - *`reverseOrder`*
          : 사전 편찬순의 역순
        - *`naturalOrder`*
          : 사전 편찬순
        - *`comparing`*
          : 비교연산자(Function 타입)를 전달받아 이를 활용하는 함수 예제에서는 string의 length를 이용
        - *`nullsFirst`/`nullsLast`*
          : null요소를 맨 앞(nullsLast = 맨 뒤)으로 보낸다
          : parameter로 들어온(not null) Comparator 메서드를 통해 선 정렬 후에 null요소 핸들링하는 점을 잊지말자

- 예제

```java
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
```

---

### Stream 중간 연산 결과 확인

- 설명
    - peek()메서드
      : 결과 스트림으로부터 요소를 소모하여, 추가로 명시된 동작을 수행한다
      : 원본 스트림에서 요소를 소모하는 것이 아님으로, 주로) 연산과 연산사이에서 결과를 확인하고 싶은 경우에 많이 사용
    - 개발자의 스트림 디버깅 요소라고 보면 된다
- 예제

```java
public class Peeking {
    public static void main(String[] args) {
        IntStream stream = IntStream.of(7,5,5,2,1,2,3,5,4,6);

        stream.peek(s -> System.out.println("원본 스트림: " +s))
                .skip(2)
                .peek(s-> System.out.println("skip(2) 실행 후: "+s))
                .limit(5)
                .peek(s-> System.out.println("limit(5) 실행 후: "+s))
                .sorted()
                .peek(s-> System.out.println("sorted() 실행 후: "+ s))
                .forEach(n-> System.out.println(n));

    }
}
```

- 결과

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/adbb6422-db73-4af4-b753-6f28906eb443/Untitled.png)

: 각 연산이 일어난 위치의 peek()연산의 출력 결과는 해당 스트림에 담긴 요소에 대한 값만 출력해준다는 것을 확인 가능

---