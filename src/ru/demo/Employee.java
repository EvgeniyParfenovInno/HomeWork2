package ru.demo;

public class Employee {
    private final String name;
    private final Integer age;
    private final String position;

    public Employee(String name, Integer age, String position) {
        this.name = name;
        this.age = age;
        this.position = position;
    }

    @Override
    public String toString() {
        return name + " (" + age + ", " + position + ")";
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getPosition() {
        return position;
    }
}
