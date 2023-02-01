package ch5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 실전 예제
 */
public class Ch5_6 {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        System.out.println("====1. 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정렬");
        List<Transaction> p1 =
                transactions.stream()
                        .filter(t -> t.getYear() == 2011)
                        .sorted(Comparator.comparing(Transaction::getValue))
                        .collect(Collectors.toList());
        for (Transaction p : p1) {
            System.out.println(p.toString());
        }
        System.out.println();

        System.out.println("====2. 거래자가 근무하는 모든 도시를 중복 없이 나열");
        List<String> p2 =
                transactions.stream()
                        .map(t -> t.getTrader().getCity())
                        .distinct().collect(Collectors.toList());
        for (String city : p2) {
            System.out.println("city = " + city);
        }
        System.out.println();

        System.out.println("====3. 케임브리지에 거주하는 거래자의 모든 트랜잭션 값");
        List<Integer> p3 =
                transactions.stream()
                        .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                        .map(Transaction::getValue)
                        .collect(Collectors.toList());
        for (Integer value : p3) {
            System.out.println("value = " + value);
        }
        System.out.println();

        System.out.println("====4. 모든 거래자의 이름을 알파벳 순으로 정렬");
        List<String> p4 =
                transactions.stream()
                        .map(t -> t.getTrader().getName())
                        .distinct()
                        .sorted()
                        .collect(Collectors.toList());
        for (String name : p4) {
            System.out.println("name = " + name);
        }
        System.out.println();

        System.out.println("====5. 밀라노에 거래자가 있는가?");
        boolean p5 = transactions.stream().
                anyMatch(t -> Objects.equals(t.getTrader().getCity(), "Milan"));
        System.out.println("p5 = " + p5);
        System.out.println();

        System.out.println("====6. 케임브리지에 거주하는 거래자의 모든 트랜잭션 값.");
        List<Integer> p6 =
                transactions.stream()
                        .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                        .map(Transaction::getValue)
                        .collect(Collectors.toList());
        for (Integer value : p6) {
            System.out.println("value = " + value);
        }
        System.out.println();

        System.out.println("====7. 전체 트랜잭션 중 최대/최소 값");
        Optional<Integer> p7Max = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);

        Optional<Integer> p7Min =
                transactions.stream()
                        .map(Transaction::getValue)
                        .reduce(Integer::min);

        p7Max.ifPresent(System.out::println);
        p7Min.ifPresent(System.out::println);
    }

    static class Trader {

        private final String name;
        private final String city;

        public Trader(String name, String city) {
            this.name = name;
            this.city = city;
        }

        public String getName() {
            return name;
        }

        public String getCity() {
            return city;
        }

        @Override
        public String toString() {
            return "Trader{" +
                    "name='" + name + '\'' +
                    ", city='" + city + '\'' +
                    '}';
        }
    }

    static class Transaction {

        private final Trader trader;
        private final int year;
        private final int value;

        public Transaction(Trader trader, int year, int value) {
            this.trader = trader;
            this.year = year;
            this.value = value;
        }

        public Trader getTrader() {
            return trader;
        }

        public int getYear() {
            return year;
        }

        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Transaction{" +
                    "trader=" + trader +
                    ", year=" + year +
                    ", value=" + value +
                    '}';
        }
    }
}
