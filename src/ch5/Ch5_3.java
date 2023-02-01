package ch5;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;

/**
 * 매핑
 */
public class Ch5_3 {

    public static void main(String[] args) {
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
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> powNumbers =
                numbers.stream()
                        .map(num -> num * num)
                        .collect(toList());
        System.out.println(powNumbers);

        System.out.println("=========2=========");
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(3, 4);
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
