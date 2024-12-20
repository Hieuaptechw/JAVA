package com.example.demo1;

import java.time.LocalDate;

public class Student {
    private int id;
    private String name;
    private int age;
    private LocalDate birthday;
    private String gender;

    public Student(int id, String name, int age, LocalDate birthday, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }
}
