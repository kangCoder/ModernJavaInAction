package ch9;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 정보 로깅
 */
public class Ch9_4 {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);

        List<Integer> result = numbers.stream()
                .peek(n -> System.out.println("from stream: " + n))
                .map(n -> n + 17)
                .peek(n -> System.out.println("after mapping: " + n))
                .filter(n -> n % 2 == 0)
                .peek(n -> System.out.println("after filtering: " + n))
                .limit(3)
                .peek(n -> System.out.println("after limit: " + n))
                .collect(Collectors.toList());


    }
}
