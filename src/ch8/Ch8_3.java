package ch8;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 맵 처리
 */
public class Ch8_3 {

    public static void main(String[] args) {
        System.out.println("1. forEach 메서드. 맵의 각 항목을 편리하게 반복할 수 있다.");
        Map<String, Integer> ageOfFriends = new java.util.HashMap<>(Map.of("Hyunsu", 25, "Olivia", 20, "Raphael", 22));
        print(ageOfFriends);

        System.out.println();
        System.out.println("2. 정렬 메서드. key or value 를 기준으로 map 을 정렬할 수 있다.");
        System.out.println("나이 순으로 오름차순 정렬.");
        ageOfFriends.entrySet()
                .stream()
                .sorted(Entry.comparingByValue())
                .forEachOrdered(System.out::println);

        System.out.println("3. getOrDefault 메서드. 키값이 없으면 기본값으로 반환한다.");
        System.out.println("19세가 없으면 0을 출력 : " + ageOfFriends.getOrDefault(19, 0));

        System.out.println();
        System.out.println("4. computeIfAbsent 메서드. 해당 키값이 없거나 널이면 키를 이용해 새 값을 계산하고 맵에 추가한다.");
        System.out.println("이름이 King 인 사람이 없으면 20세 King 을 map 에 추가한다.");
        ageOfFriends.computeIfAbsent("King", k -> 20);
        print(ageOfFriends);

        System.out.println();
        System.out.println("5. computeIfPresent 메서드. 해당 키값에 대응하는 value 가 있다면, 새 값을 계산하고 맵에 추가한다.");
        System.out.println("이름이 Hyunsu인 사람이 있으면 나이를 26세로 수정한다.");
        ageOfFriends.computeIfPresent("Hyunsu", (k, v) -> 26);
        print(ageOfFriends);

        System.out.println();
        System.out.println("6. 삭제 패턴. 키가 특정한 값과 연관되었을 때 항목을 제거하는 오버로드 버전 메서드.");
        System.out.println("나이가 20세인 King 이 있다면 맵에서 지운다.");
        ageOfFriends.remove("King", 20);
        print(ageOfFriends);

        System.out.println();
        System.out.println("7. 교체 패턴. 각 항목의 값을 교체한다. replaceAll, Replace");
        System.out.println("나이에 +2씩 더한다.");
        ageOfFriends.replaceAll((name, age) -> age + 2);
        print(ageOfFriends);

        System.out.println();
        System.out.println("8. merge 메서드. 두 개의 맵을 합치거나 맵의 초기화 검사를 구현할 수 있다.");
        System.out.println("두 그룹의 이름, 연락처 맵을 합쳐보자. 이때 키 값이 중복될 경우 & 으로 묶어서 처리해보자.");
        Map<String, String> m1 = Map.ofEntries(
                Map.entry("Elica", "1234"), Map.entry("Edward", "4567"), Map.entry("Eva", "1555")
        );
        Map<String, String> m2 = Map.ofEntries(
                Map.entry("Even", "1237"), Map.entry("Edward", "4569"), Map.entry("Alice", "1558")
        );

        Map<String, String> everyone = new HashMap<>(m1);
        m2.forEach((name, contact) ->
                everyone.merge(name, contact, (contact1, contact2) -> contact1 + " & " + contact2)
        );
        System.out.println("everyone = " + everyone);

        System.out.println();
        System.out.println("영화를 몇 회 시청했는지 기록하는 맵에서 처음 시청한 경우, 시청횟수를 1로 초기화해보자.");
        Map<String, Long> moviesToCount = new HashMap<>();
        moviesToCount.merge("James", 1L, (name, count) -> count + 1L); //count가 있다면 1 추가하고 없다면 1.
        System.out.println("moviesToCount = " + moviesToCount);
    }

    private static void print(Map<String, Integer> ageOfFriends) {
        ageOfFriends.forEach((name, age) -> System.out.println("name = " + name + ", age = " + age));
    }
}
