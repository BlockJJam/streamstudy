### 스트림은 어떻게 생성할 수 있을까?

- 스트림 API는 다음의 다양한 데이터 소스에서 생성이 가능하다
1. 컬렉션
   : `List`, `Set`, `Queue`, `Map` ( Map은 Collection 인터페이스를 상속받지 않으나 컬렉션 프레임워크에 속함 )
2. 배열
   : `int[] odds = {1,3,4,5};`
   : `String[] weeks = {"월", "화", "수", "목"};`
3. 가변 매개변수
   : `void sum(String...str){}`
4. 지정된 범위의 연속된 정수
   : `IntStream stream1 = IntStream.range(1,4);`
   : `IntStream stream2 = IntStream.rangeClosed(1,4);`
5. 특정 타입의 난수들
   : `IntStream stream = new Random().ints(4);`
6. 람다 표현식
   : `IntStream stream = Stream.iterate(2,n -> n+2);`
7. 파일
   : `String<String> stream  Files.lines(Path path);`
8. 빈 스트림
   : `Stream<Object> stream = Stream.empty();`

---

### 컬렉션에서 어떻게 Stream을 생성할까??

- Collection인터페이스는 컬렉션들의 최상위 인터페이스
    - 이 인터페이스에는 stream()이 정의 되어있고, 이를 구현한 모든 구현체들은 stream()으로 스트림 생성
    - parallelStream()메서드는 병렬처리 가능한 스트림을 생성

### 배열에서는 어떻게 Stream을 생성할까?

- 배열에서도 스트림을 생성할 수 있는데, Arrays 클래스의 stream()을 사용하면 된다
- 기본타입의 int, long, double형을 저장할 수 있는 배열에 관한 스트림도 별도 정의 되어있다
    - package: java.util.stream → IntStream, LongStream, DoubleStream 인터페이스로 각각 제공

### 가변 매개변수로는 어떻게 Stream을 생성할까?

- 가변 매개변수를 전달받아 스트림을 생성할 수 있다
- Stream의 of()를 사용하면 가변 매개변수를 받을 수 있게 된다

### 지정된 범위의 연속된 정수로는 어떻게 Stream을 생성할까?

- 연속된 정수를 스트림으로 생성하려고, IntStream이나 LongStream 인터페이스에는 range()와 rangeClosed()가 정의되어있다
    - range() 명시된 시작 정수를 포함, 마지막 정수는 포함하지 않는 스트림을 생성
    - rangeClosed() 는 명시된 시작 정수 + 마지막 정수 모두 포함하는 스트림 생성


### 특정 타입의 난수들

- 특정타입의 난수로 이루어진 Random클래스에는 ints(), longs(), doubles()가 정의
    - 위 메서드들이 받을 수 있는 Stream의 크기는 long타입으로 전달받을 수 있다
    - 또한 위 메서드들이 매개변수를 전달받지 못하면, 무한 스트림을 반환 → limit()을 사용해서 크기 제한 필요

### 람다 표현식

- 람다 표현식을 매개변수로 전달받아, 람다 표현식에 의해 반환되는 값을 요소로 하는 무한 스트림을 생성하기 위해
  → Stream에는 iterate(), generate()가 정의
    - iterate()는 시드로 명시된 값을 람다에 사용 → 반환된 값을 다시 시드로 사용하는 무한 스트림
    - generate()는 매개변수가 없는 람다 표현식을 사용 → 반환된 값으로 무한 스트림 생성


### 빈 스트림 생성

- Stream 클래스의 empty()를 사용하여 생성