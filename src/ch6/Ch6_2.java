package ch6;

import static java.util.stream.Collectors.*;

import ch4.Dish;
import ch4.Dish.Type;
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;


/**
 * 리듀싱과 요약
 */
public class Ch6_2 {

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
        long howManyDishes = menu.stream().count(); // collect(Collectors.count());

        System.out.println("1. 스트림값에서 최댓값과 최솟값 검색");
        System.out.println("Collectors.maxBy, Collectors.minBy로 스트림의 최댓값과 최솟값을 구할 수 있다.");
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish = menu.stream().collect(maxBy(dishCaloriesComparator));
        mostCalorieDish.ifPresent(System.out::println);

        System.out.println();

        System.out.println("2. 요약 연산");
        int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
        System.out.println("메뉴 리스트의 총 칼로리: " + totalCalories);

        double avgCalories = menu.stream().collect(averagingDouble(Dish::getCalories));
        System.out.println("avgCalories = " + avgCalories);

        IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println("menuStatistics = " + menuStatistics);
        System.out.println();

        System.out.println("3. 문자열 연결");
        String shortMenu = menu.stream().map(Dish::getName).collect(joining(", ")); //toString을 지원한다면 menu.stream().collect(joining())
        System.out.println("shortMenu = " + shortMenu);

        System.out.println();

        System.out.println("4. 범용 리듀싱 요약 연산");
        System.out.println("지금까지 했던 것들을 reducing 메서드로 만들어진 컬렉터로도 만들 수 있다.");
        int totalCalories2 = menu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
        System.out.println("totalCalories2 = " + totalCalories2);

    }
}
