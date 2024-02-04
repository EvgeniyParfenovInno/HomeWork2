package ru.demo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        var ints = List.of(5, 2, 10, 9, 4, 3, 10, 1, 13);

        System.out.println("1. Список без дубликатов: " + ints.stream()
                .distinct()
                .toList());
        System.out.println("2. Третье наибольшее: " + ints.stream()
                .sorted(Comparator.reverseOrder())
                .skip(2)
                .findFirst()
                .orElse(null));
        System.out.println("3. Третье наибольшее уникальное: " + ints.stream()
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

        System.out.println("4. 3 инженера: " + empl.stream()
                .filter(e -> ENGINEER.equals(e.getPosition()))
                .sorted((o1, o2) -> o2.getAge() - o1.getAge())
                .limit(3)
                .map(Employee::getName)
                .toList());
        System.out.println("5. Ср. возраст инженеров: " + empl.stream()
                .filter(e -> ENGINEER.equals(e.getPosition()))
                .map(Employee::getAge)
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0));

        var str1 = List.of("Сотрудник", "Инженер", "Технолог", "Директор", "Имя", "Возраст", "Должность");

        System.out.println("6. Слово с наибольшнй длиной: " + str1.stream()
                .max(Comparator.comparingInt(String::length))
                .orElse(null));

        String str2 = "один два три четыре пять четыре три два один ноль";

        System.out.println("7. Слово/количество: " + Arrays.stream(str2.split(" "))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));

        System.out.println("8. Сортировка по длине и алфавиту: " + str1.stream()
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

        System.out.println("9. Самое длинное слово: " + str3.stream()
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .min((o1, o2) -> o2.length() - o1.length())
                .orElse(null));
    }
}
