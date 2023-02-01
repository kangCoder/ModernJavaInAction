package ch5;

import static java.util.stream.Collectors.toList;

import ch4.Dish;
import ch4.Dish.Type;
import java.util.Arrays;
import java.util.List;

/**
 * 스트림 슬라이싱
 */
public class Ch5_2 {

    public static void main(String[] args) {
        List<Dish> specialMenu = Arrays.asList(
                new Dish("season fruit", true, 120, Type.OTHER),
                new Dish("prawns", false, 300, Type.FISH),
                new Dish("rice", true, 350, Type.OTHER),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Type.OTHER)
        );

        //리스트가 이미 정렬돼있다면 반복 작업을 중간에 잘라 효율을 올릴 수 있다.
        List<Dish> sliceMenu1 =
                specialMenu.stream()
                        .takeWhile(dish -> dish.getCalories() < 320)
                        .collect(toList());

        List<Dish> sliceMenu2 =
                specialMenu.stream()
                        .dropWhile(dish -> dish.getCalories() < 320)
                        .collect(toList());
        //위의 specialMenu 는 칼로리를 기준으로 정렬돼있는 상태. -> takeWhile, dropWhile 을 사용하면 반복작업 없이 자를 수 있다.

        List<Dish> twoMeats =
                specialMenu.stream()
                        .filter(dish -> dish.getType() == Type.MEAT)
                        .limit(2)
                        .collect(toList());
    }
}
