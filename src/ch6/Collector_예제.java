package ch6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class Collector_예제 {

    private static List<Person> randomPerson(int size) {
        Random r = new Random();
        List<Person> people = new ArrayList<>();
        String[] family = new String[]{ "Kim", "Kang", "Lee", "Park", "Oh", "Bang" };
        Boolean[] isMale = new Boolean[]{true, false};

        for(int i=0; i<size; i++) {
            people.add(new Person(family[r.nextInt(6)], isMale[r.nextInt(2)], r.nextInt(20) + 10));
        }

        return people;
    }
    public static void main(String[] args) {
        List<Person> people = randomPerson(10);

        System.out.println("[People]");
        people.forEach(System.out::println);
        System.out.println("==========================================");

        Map<String, List<Person>> nameGroup
                = people.stream().collect(new MyCollector());

        Set<String> nameSet = nameGroup.keySet();
        for (String name : nameSet) {
            System.out.println("[name = " + name + "]");
            List<Person> list = nameGroup.get(name);
            list.forEach(System.out::println);
        }
    }


    /**
     * Person: 스트림 요소의 형식
     * List<Person>: 누적자의 형식
     * Map<String, List<Person>>: 최종적으로 반환되는 결과. key=String, value=Person을 담은 List
     */
    static class MyCollector implements Collector<Person, List<Person>, Map<String, List<Person>>> {

        @Override
        public Supplier<List<Person>> supplier() {
            return ArrayList::new;
            //return () -> new ArrayList<Person>();
        }

        @Override
        public BiConsumer<List<Person>, Person> accumulator() {
            return List::add;
            //return (list, person) -> list.add(person);
        }

        @Override
        public BinaryOperator<List<Person>> combiner() {
            return (list1, list2) -> {
                list1.addAll(list2);
                return list1;
            };
        }

        //누적 과정을 종료하고 최종 결과로 반환.
        //List<Person> -> Map<String, List<Person>으로 변환하여 return.
        @Override
        public Function<List<Person>, Map<String, List<Person>>> finisher() {
            return people -> {
                Map<String, List<Person>> map = new HashMap<>();
                for (Person person : people) {
                    String name = person.name;
                    if(!map.containsKey(name)) {
                        map.put(name, new ArrayList<>());
                    }
                    map.get(name).add(person);
                }

                return map;
            };
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.emptySet();
        }
    }

}
