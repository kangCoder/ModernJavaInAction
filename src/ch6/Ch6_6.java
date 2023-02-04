package ch6;

import static ch6.Ch6_4.partitionPrimes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 커스텀 컬렉터를 구현해서 성능 개선하기
 */
public class Ch6_6 {

    public static void main(String[] args) {
        System.out.println("소수를 판별하는 컬렉터 개선해보기");
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            partitionPrimes(1_000_000);
            long duration = (System.nanoTime() - start);
            if (duration < fastest) {
                fastest = duration;
            }
        }
        System.out.println("기존 Fastest execution done in " + fastest + " msecs");

        fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            partitionPrimesWithCustomCollector(1_000_000);
            long duration = (System.nanoTime() - start);
            if (duration < fastest) {
                fastest = duration;
            }
        }
        System.out.println("커스텀 Fastest execution done in " + fastest + " msecs");
    }

    private static Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector(int n) {
        return IntStream.rangeClosed(2, n).boxed().collect(
                () -> new HashMap<Boolean, List<Integer>>() {
                    {
                        put(true, new ArrayList<>());
                        put(false, new ArrayList<>());
                    }
                },
                (acc, candidate) -> acc.get(isPrime(acc.get(true), candidate)).add(candidate),
                (map1, map2) -> {
                    map1.get(true).addAll(map2.get(true));
                    map1.get(false).addAll(map2.get(false));
                }
        );
    }

    private static boolean isPrime(List<Integer> primes, int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return primes.stream()
                .takeWhile(i -> i <= candidate)
                .noneMatch(i -> candidate % i == 0);
    }

    //Collector<스트림 요소의 형식, 누적자 형식, 수집 연산의 결과 형식>
    static class PrimeNumbersCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {

        @Override
        public Supplier<Map<Boolean, List<Integer>>> supplier() {
            return () -> new HashMap<>() {
                {
                    put(true, new ArrayList<>());
                    put(false, new ArrayList<>());
                }
            };
        }

        @Override
        public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
            return (Map<Boolean, List<Integer>> acc, Integer candidate) -> {
                acc.get(isPrime(acc.get(true), candidate)).add(candidate);
            };
        }

        @Override
        public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
            return (Map<Boolean, List<Integer>> map1, Map<Boolean, List<Integer>> map2) -> {
                map1.get(true).addAll(map2.get(true));
                map2.get(false).addAll(map2.get(false));
                return map1;
            };
        }

        @Override
        public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
            return Function.identity();
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
        }
    }
}
