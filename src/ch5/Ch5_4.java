package ch5;

import ch4.Dish;
import ch4.Dish.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 검색과 매칭 예제
 */
public class Ch5_4 {

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

        //1. predicate 가 적어도 한 요소와 일치하는지 확인.
        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("메뉴 중에 1개 이상의 야채가 존재.");
        }

        //2. predicate 가 모든 요소와 일치하는지 확인.
        boolean isHealthy = menu.stream().allMatch(dish -> dish.getCalories() < 1000); //true

        boolean isHealthy2 = menu.stream()
                .noneMatch(dish -> dish.getCalories() >= 1000); //noneMatch 는 반대로 주어진 predicate 와 일치하는게 없는지 확인. 전부 없다면 true

        //3. 요소 검색
        Optional<Dish> dish =
                menu.stream()
                        .filter(Dish::isVegetarian)
                        .findAny(); //findAny -> predicate 를 만족하는 아무거나 하나 검색.

        dish.ifPresent(d -> System.out.println(d.getName()));

        //4. 첫 번째 요소 찾기
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> first =
                numbers.stream()
                        .map(n -> n * n)
                        .filter(n -> n % 3 == 0)
                        .findFirst();

        first.ifPresent(System.out::println);
    }

}
