package ch4;

import static java.util.stream.Collectors.toList;

import ch4.Dish.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Chapter4Main {

    public static void main(String[] args) {
        //기본 메뉴
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

        //메뉴에서 칼로리가 300이 넘는 음식 중 제일 높은 3개의 음식의 이름을 가져와서 리스트화
        List<String> threeHighCaloricDishNames =
                menu.stream()
                        .filter(dish -> dish.getCalories() > 300)
                        .map(Dish::getName)
                        .limit(3)
                        .collect(toList());

        //System.out.println(threeHighCaloricDishNames);


        //외부 반복과 내부 반복

        //1. 컬렉션이 사용하는 외부 반복
        List<String> names1 = new ArrayList<>();
        for (Dish dish : menu) {
            names1.add(dish.getName());
        }

        //2. 스트림이 사용하는 내부 반복 (외부적으로 반복자를 사용하지 않음).
        List<String> names2 = menu
                .stream()
                .map(Dish::getName)
                .collect(toList());


        // 스트림 연산 과정 살펴보기
        List<String> names =
                menu.stream()
                        .filter(dish -> {
                            System.out.println("filtering: " + dish.getName());
                            return dish.getCalories() > 300;
                        })
                        .map(dish -> {
                            System.out.println("mapping: " + dish.getName());
                            return dish.getName();
                        })
                        .limit(3)
                        .collect(toList());
        System.out.println(names);


    }

}
