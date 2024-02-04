package ru.demo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        var ints = List.of(5, 2, 10, 9, 4, 3, 10, 1, 13);

        System.out.printf("1. Список без дубликатов: %s%n", ints.stream()
                .distinct()
                .toList());
        System.out.printf("2. Третье наибольшее: %s%n", ints.stream()
                .sorted(Comparator.reverseOrder())
                .skip(2)
                .findFirst()
                .orElse(null));
        System.out.printf("3. Третье наибольшее уникальное: %s%n", ints.stream()
                .sorted(Comparator.reverseOrder())
                .distinct()
                .skip(2)
                .findFirst()
                .orElse(null));

        final String ENGINEER = "Инженер";
        var empl = List.of(
                new Employee("Иван", 25, ENGINEER),
                new Employee("Евгений", 32, ENGINEER),
                new Employee("Петр", 43, ENGINEER),
                new Employee("Сергей", 28, "Технолог"),
                new Employee("Андрей", 33, ENGINEER),
                new Employee("Алексей", 48, "Директор")
        );

        System.out.printf("4. 3 инженера: %s%n", empl.stream()
                .filter(e -> ENGINEER.equals(e.getPosition()))
                .sorted((o1, o2) -> o2.getAge() - o1.getAge())
                .limit(3)
                .map(Employee::getName)
                .toList());
        System.out.printf("5. Ср. возраст инженеров: %s%n", empl.stream()
                .filter(e -> ENGINEER.equals(e.getPosition()))
                .map(Employee::getAge)
                .mapToInt(Integer::intValue)
                .average()
                .orElse(Double.NaN));

        var str1 = List.of("Сотрудник", "Инженер", "Технолог", "Директор", "Имя", "Возраст", "Должность");

        System.out.printf("6. Слово с наибольшей длиной: %s%n", str1.stream()
                .max(Comparator.comparingInt(String::length))
                .orElse(null));

        String str2 = "один два три четыре пять четыре три два один ноль";

        System.out.println("7. Слово/количество: " + Arrays.stream(str2.split(" "))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));

        System.out.printf("8. Сортировка по длине и алфавиту: %s%n", str1.stream()
                .sorted((o1, o2) -> {
                            if (o1.length() == o2.length()) {
                                return o1.compareTo(o2);
                            }
                            return o1.length() - o2.length();
                        }
                ).toList());

        var str3 = List.of("один два три четыре пять",
                "белый синий красный зеленый сиреневый",
                "сто двести триста четыреста пятьсот");

        System.out.printf("9. Самое длинное слово: %s%n", str3.stream()
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .max(Comparator.comparingInt(String::length))
                .orElse(null));
    }
}
