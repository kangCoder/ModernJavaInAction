package ch8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 리스트와 집합 처리
 */
public class Ch8_2 {

    public static void main(String[] args) {
        System.out.println("1. removeIf 메서드. 프레디케이트를 만족하는 인자를 지운다.");
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        numbers.removeIf(n -> n % 2 == 0);
        System.out.println("numbers = " + numbers);

        System.out.println("2. replaceAll 메서드. 리스트의 각 요소를 새로운 요소로 바꿀 수 있다.");
        System.out.println("before numbers = " + numbers);
        numbers.replaceAll(n -> n * 2);
        System.out.println("after numbers = " + numbers);
    }
}
