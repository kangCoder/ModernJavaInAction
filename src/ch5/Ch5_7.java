package ch5;

import ch4.Dish;
import ch4.Dish.Type;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 숫자형 스트림
 */
public class Ch5_7 {

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

        System.out.println("======1. 기본형 특화 스트림======");

        int calories = menu.stream().mapToInt(Dish::getCalories).sum(); //객체 -> 기본형
        System.out.println("calories = " + calories);

        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> stream = intStream.boxed(); //특화 스트림 -> 일반 스트림

        OptionalInt maxCalories = menu.stream().mapToInt(Dish::getCalories).max();
        int max = maxCalories.orElse(1); //기본값을 명시적으로 지정할 수 있다.

        //2. 숫자 범위
        System.out.println("======2. 숫자 범위======");
        IntStream evenStream = IntStream.rangeClosed(1, 100)
                .filter(i -> i % 2 == 0); //rangeClosed -> [1, 100] / range -> (1, 100)
        System.out.println("evenStream.count() = " + evenStream.count());

        //3. 활용: 피타고라스 수
        System.out.println("======3. 활용: 피타고라스 수======");
        Stream<double[]> pythagorean = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100).mapToObj(b -> new double[]{a, b, Math.sqrt((a * a) + (b * b))}))
                .filter(t -> t[2] % 1 == 0);

        pythagorean.forEach(arr -> {
            System.out.print("[ ");
            for (double v : arr) {
                System.out.print(v + " ");
            }
            System.out.println("]");
        });
    }
}
