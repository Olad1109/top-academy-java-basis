package main.java.com.training.model_view_controller.code_002;

import static main.java.com.training.model_view_controller.code_002.A1.SexType.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class A1 {

    public static void main (String[] args) {
        A1 a1 = new A1();
        a1.dataCollection();
    }

    enum SexType {
        MALE,
        FEMALE
    }

    void dataCollection() {
        // нахождение среднего значения
        Double averageAge = StudentData.getPersons().
                collect(Collectors.averagingInt(Student::getAge));
        System.out.println("Средний возраст" + averageAge);

        // статистика
        IntSummaryStatistics sum = StudentData.getPersons().
                collect(Collectors.summarizingInt(Student::getAge));
        System.out.println(sum);

        // группировка
        Map<Object, List<Student>> group = StudentData.getPersons().
                collect(Collectors.groupingBy(Student::getAge));
        System.out.println(group);
    }

    static class Group {
        private final String name;
        private final List<Student> persons = new ArrayList<>();

        Group(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }

        void addPerson(Student person) {
            persons.add(person);
        }

        public List<Student> getPersons() {
            return Collections.unmodifiableList(persons);
        }

    }

    static class Student {
        private final String name;
        private final int age;
        private final Group group;
        private SexType sexType;

        public Student(String name, int age, Group group, SexType sexType) {
            this.name = name;
            this.age = age;
            this.group = group;
            group.addPerson(this);
        }

        public String getName() {
            return name;
        }

        int getAge() {
            return age;
        }

        Group getGroup() {
            return group;
        }
        SexType getSexType() {
            return sexType;
        }

        @Override
        public String toString() {
            return "Группа:" + group.getName() +
                    " " +
                    "Имя:" + name +
                    " " +
                    "Возраст:" + age;
        }
    }


    public static class StudentData {

        public static Stream<Student> getPersons() {
            Group group1 = new Group("java1");
            Group group2 = new Group("java2");
            PersonFactory<Student> factory = Student::new;
            return Stream.of(
                    factory.create("Василий", 18, group1, MALE),
                    factory.create("Петр", 25, group1, MALE),
                    factory.create("Роман", 33, group2, MALE),
                    factory.create("Дарья", 19, group2, FEMALE),
                    factory.create("Анна", 24, group1, FEMALE),
                    factory.create("Александр", 16, group2, MALE),
                    factory.create("Федор", 52, group2, MALE),
                    factory.create("Антонн", 41, group1, MALE),
                    factory.create("Владимир", 49, group1, MALE),
                    factory.create("Константин", 21, group1, MALE),
                    factory.create("Татьяна", 56, group1, FEMALE),
                    factory.create("Марьяна", 64, group2, FEMALE));
        }

        @FunctionalInterface
        interface PersonFactory<P extends Student> {
            P create(String name, int age, Group group, SexType sexType);
        }
    }
}
