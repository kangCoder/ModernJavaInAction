package ch6;

public class Person {
    String name;
    Boolean isMale;
    Integer age;

    public Person(String name, Boolean isMale, Integer age) {
        this.name = name;
        this.isMale = isMale;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", isMale=" + isMale +
                ", age=" + age +
                '}';
    }
}
