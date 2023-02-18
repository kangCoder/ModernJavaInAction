package ch9;

import java.util.Arrays;
import java.util.List;

/**
 * 람다 표현식과 스트림에서의 디버깅
 */
public class Debugging {

    public static int divideByZero(int n) {
        return n / 0;
    }

    public static void main(String[] args) {
        List<Point> points = Arrays.asList(new Point(12, 2), null);

        System.out.println("1. 일반적인 경우의 스택 트레이스");
        for (Point point : points) {
            System.out.println(point.getX());
        }

        System.out.println("2. 람다 표현식에서의 스택 트레이스");
//        points.stream()
//                .map(Point::getX)
//                .forEach(System.out::println);

        System.out.println("3. 람다 표현식에서의 스택 트레이스 (메서드 참조)");
        List<Integer> numbers = Arrays.asList(1,2,3);
        numbers.stream()
                .map(Debugging::divideByZero).forEach(System.out::println);
    }

}
