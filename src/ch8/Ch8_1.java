package ch8;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 컬렉션 팩토리
 */
public class Ch8_1 {

    public static void main(String[] args) {
        System.out.println("1. 리스트 팩토리, List.of()를 이용하여 (불변)리스트를 만들 수 있다.");
        List<String> friends = List.of("Hyunsu", "Olivia", "Raphael");
        //friends.add("Chih-Chun"); -> java.lang.UnsupportedOperationException 발생.
        System.out.println("friends = " + friends);

        System.out.println();
        System.out.println("2. 집합 팩토리. Set.of()를 이용하여 바꿀 수 없는 집합을 만들 수 있다.");
        Set<String> friendsSet = Set.of("Hyunsu", "Olivia", "Raphael");
        System.out.println("friendsSet = " + friendsSet);

        System.out.println();
        System.out.println("3. 맵 팩토리, Map.of()와 Map.ofEntries()로 맵을 만들 수 있다.");
        Map<String, Integer> ageOfFriends = Map.of("Hyunsu", 25, "Olivia", 20, "Raphael", 22);
        System.out.println("ageOfFriends = " + ageOfFriends);

        Map<String, Integer> ageOfFriends2 = Map.ofEntries(
                Map.entry("Hyunsu", 25), Map.entry("Olivia", 20), Map.entry("Raphael", 22));
        System.out.println("ageOfFriends2 = " + ageOfFriends2);
    }
}
