package ch5;

import ch4.Dish;
import ch4.Dish.Type;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 필터림
 */
public class Ch5_1 {

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
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

        System.out.println("====1. predicate====");
        List<Dish> vegetarian = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());

        System.out.println("====2. 고유 요소====");
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct() //중복 제거
                .forEach(System.out::println);
    }
}
