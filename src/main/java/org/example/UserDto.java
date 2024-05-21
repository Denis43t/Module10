package org.example;

public class UserDto {
    private String name;
    private int age;

    @Override
    public String toString() {
        return "UserDto{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public UserDto(String name, int age) {
        this.name = name;
        this.age = age;
    }

}
