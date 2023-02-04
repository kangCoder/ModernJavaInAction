package ch6;

import static java.util.stream.Collectors.*;

import ch4.Dish;
import ch4.Dish.Type;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * 그룹화
 */
public class Ch6_3 {

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
        Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(groupingBy(Dish::getType));
        System.out.println("dishesByType = " + dishesByType);

        System.out.println("1. 그룹화된 요소 조작");
        Map<Dish.Type, List<String>> dishNamesByType = menu.stream()
                .collect(groupingBy(Dish::getType, mapping(Dish::getName, toList())));
        System.out.println("dishNamesByType = " + dishNamesByType);

        System.out.println();

        System.out.println("2. 다수준 그룹화");
        System.out.println("두 인수를 받는 팩토리 메서드 Collectors.groupingBy() 를 사용할 수 있다.");
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishedByTypeCaloricLevel
                = menu.stream().collect(
                //첫 번째 그룹화
                groupingBy(Dish::getType,
                        //두 번쨰 그룹화
                        groupingBy(dish -> {
                            if (dish.getCalories() <= 400) {
                                return CaloricLevel.DIET;
                            } else if (dish.getCalories() <= 700) {
                                return CaloricLevel.NORMAL;
                            } else {
                                return CaloricLevel.FAT;
                            }
                        }))
        );
        System.out.println("dishedByTypeCaloricLevel = " + dishedByTypeCaloricLevel);

        System.out.println();

        System.out.println("3. 서브그룹으로 데이터 수집");
        System.out.println("첫 번째 그루핑으로 넘겨주는 컬렉터의 형식에는 제한이 따로 없다.");
        Map<Dish.Type, Long> typesCount = menu.stream().collect(groupingBy(Dish::getType, counting()));
        System.out.println("typesCount = " + typesCount);

        Map<Dish.Type, Optional<Dish>> mostCaloricByType
                = menu.stream().collect(groupingBy(Dish::getType, maxBy(Comparator.comparingInt(Dish::getCalories))));
        System.out.println("mostCaloricByType = " + mostCaloricByType);

        Map<Dish.Type, Integer> totalCaloriesByType
                = menu.stream().collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));
        System.out.println("totalCaloriesByType = " + totalCaloriesByType);

        Map<Dish.Type, Set<CaloricLevel>> caloricLevelByType
                = menu.stream().collect(groupingBy(Dish::getType, mapping(dish -> {
                    if (dish.getCalories() <= 400) {
                        return CaloricLevel.DIET;
                    } else if (dish.getCalories() <= 700) {
                        return CaloricLevel.NORMAL;
                    } else {
                        return CaloricLevel.FAT;
                    }
                }, toSet()
        )));
        System.out.println("caloricLevelByType = " + caloricLevelByType);
    }
}
