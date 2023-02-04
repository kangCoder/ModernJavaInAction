package ch6;

import static java.util.stream.Collectors.*;

import ch4.Dish;
import ch4.Dish.Type;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * 분할
 */
public class Ch6_4 {

    public enum CaloricLevel {DIET, NORMAL, FAT}

    public static List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Type.MEAT),
            new Dish("beef", false, 700, Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Type.OTHER),
            new Dish("rice", true, 350, Type.OTHER),
            new Dish("season fruit", true, 120, Type.OTHER),
            new Dish("pizza", true, 550, Type.OTHER),
            new Dish("prawns", false, 300, Type.FISH),
            new Dish("salmon", false, 450, Type.FISH)
    );

    public static void main(String[] args) {
        System.out.println("1. 분할의 장점");
        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType
                = menu.stream().collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
        System.out.println("vegetarianDishesByType = " + vegetarianDishesByType);

        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian
                = menu.stream().collect(partitioningBy(Dish::isVegetarian,
                collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
        System.out.println("mostCaloricPartitionedByVegetarian = " + mostCaloricPartitionedByVegetarian);

        System.out.println("퀴즈");
        Map<Boolean, Map<Boolean, List<Dish>>> q1 = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian, partitioningBy(d -> d.getCalories() > 500)));
        System.out.println("q1 = " + q1);

        //menu.stream().collect(partitioningBy(Dish::isVegetarian, partitioningBy(Dish::getType))); -> 에러!

        Map<Boolean, Long> q3 = menu.stream().collect(partitioningBy(Dish::isVegetarian, counting()));
        System.out.println("q3 = " + q3);

        System.out.println();

        System.out.println("2. 숫자를 소수와 비소수로 분할하기");
        Map<Boolean, List<Integer>> isPrimeMap = partitionPrimes(237);
        System.out.println("isPrimeMap = " + isPrimeMap);
    }

    private static boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double)candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
    }

    public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(partitioningBy(candidate -> isPrime(candidate)));
    }
}
