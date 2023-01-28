package ch5;

import static java.util.stream.Collectors.toList;

import ch4.Dish;
import ch4.Dish.Type;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 스트림 활용
 */
public class Chapter5Main {

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


        //flatMap
        List<String> words = Arrays.asList("Modern", "Java", "In", "Action");
        List<String> uniqueCharacters =
                words.stream()
                        .map(word -> word.split(""))
                        .flatMap(Arrays::stream)
                        .distinct()
                        .collect(toList());


        //매핑 예제
        System.out.println("=========1=========");
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
        List<Integer> powNumbers =
                numbers.stream()
                        .map(num -> num * num)
                        .collect(toList());
        System.out.println(powNumbers);

        System.out.println("=========2=========");
        List<Integer> list1 = Arrays.asList(1,2,3);
        List<Integer> list2 = Arrays.asList(3,4);
        List<int[]> pairList =
                list1.stream()
                        .flatMap(i -> list2.stream()
                                .map(j -> new int[]{i, j}))
                        .collect(toList());
        for (int[] ints : pairList) {
            System.out.print("[");
            for (int anInt : ints) {
                System.out.print(anInt + ",");
            }
            System.out.println("]");
        }

        System.out.println("=========3=========");
        List<int[]> pairs =
                pairList.stream()
                        .filter(arr -> (arr[0] + arr[1]) % 3 == 0)
                        .collect(toList());
        for (int[] ints : pairs) {
            System.out.print("[");
            for (int anInt : ints) {
                System.out.print(anInt + ",");
            }
            System.out.println("]");
        }

        List<int[]> pairs2 =
                list1.stream()
                        .flatMap(i -> list2.stream()
                                .filter(j -> (i + j) % 3 == 0)
                                .map(j -> new int[]{i, j}))
                        .collect(toList());
        for (int[] ints : pairs2) {
            System.out.print("[ ");
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println("]");
        }
    }

}
