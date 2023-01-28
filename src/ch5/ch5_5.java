package ch5;

import ch4.Dish;
import ch4.Dish.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 리듀싱
 */
public class ch5_5 {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

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

        //1. 요소의 합
        int sum = numbers.stream().reduce(0, (a, b) -> a + b); //초기값 0 존재.
        System.out.println(sum);

        Optional<Integer> sum2 = numbers.stream().reduce((a, b) -> a + b); //초기값이 없음.
        sum2.ifPresent(System.out::println);

        //2. 최댓값과 최솟값
        Optional<Integer> max = numbers.stream().reduce((a, b) -> a > b ? a : b); //Integer::max
        Optional<Integer> min = numbers.stream().reduce((a, b) -> a < b ? a : b); //Integer::min
        max.ifPresent(System.out::println);
        min.ifPresent(System.out::println);

        //map 과 reduce 를 연결하는 맵 리듀스 패턴.
        Optional<Integer> dishCount = menu.stream().map(dish -> 1).reduce((a, b) -> a + b);
        dishCount.ifPresent(System.out::println);

        long count = menu.stream().count(); //menu.size() 해도 되긴 하던데..
        System.out.println("count = " + count);
    }

}
