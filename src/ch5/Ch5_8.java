package ch5;

import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 스트림 만들기
 */
public class Ch5_8 {

    public static void main(String[] args) {
        System.out.println("======1. 값으로 스트림 만들기======");
        Stream<String> stream = Stream.of("Modern", "Java", "In", "Action"); //Stream.of 메서드로 스트림을 만들 수 있다. (정적 메서드)
        stream.map(String::toUpperCase).forEach(System.out::println);

        System.out.println();

        System.out.println("======2. null이 될 수 있는 객체로 스트림 만들기======");
        //자바 9부터는 null이 될 수있는 객체를 스트림으로 만들 수 있게 됐다.
        String homeValue = System.getProperty("home"); //System.getProperty -> 제공된 키에 대응하는 속성이 없으면 null 을 반환.
        Stream<String> homeValueStream = Stream.ofNullable(System.getProperty("home"));

        Stream<String> values =
                Stream.of("config", "home", "user")
                        .flatMap(key -> Stream.ofNullable(System.getProperty(key)));

        System.out.println();

        System.out.println("======3. 배열로 스트림 만들기======");
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int sum = Arrays.stream(numbers).sum(); //정적 메서드 Arrays.stream 으로 배열을 스트림으로 만들 수 있다.

        System.out.println();

        System.out.println("======4. 파일로 스트림 만들기======");

        System.out.println();

        System.out.println("======5. 함수로 무한 스트림 만들기======");
        //Stream.iterate, Stream.generate 두 연산으로 스트림에서 무제한으로 값을 계산할 수 있다. 보통 limit 과 같이 계산을 제한해서 사용한다.
        Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println); //초기값 0, 2씩 계속 더해가며 출력한다.

        //피보나치수열 집합
        System.out.println("//피보나치수열 집합");
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]}).limit(20)
                .forEach(t -> System.out.println("(" + t[0] + ", " + t[1] + ")"));

        //generate
        Stream.generate(Math::random).limit(5).forEach(System.out::println);

        System.out.println("generate 로 피보나치 수열");
        IntSupplier fib = new IntSupplier() {
            private int prev = 0;
            private int cur = 1;
            @Override
            public int getAsInt() {
                int oldPrev = this.prev;
                int next = this.prev + this.cur;
                this.prev = this.cur;
                this.cur = next;
                return oldPrev;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);
        System.out.println();
    }
}
