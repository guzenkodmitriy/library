package com.guzenko.springcourse.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
    private int id;

    @NotEmpty(message = "ФИО должна быть заполнена")
    @Size(min = 2, max = 100, message = "ФИО должно быть от 2 до 100 символов")
    private String name;

    @Min(value = 1900, message = "Год рождения должен быть больше, чем 1900")
    private int birthday;

    public Person() {

    }

    public Person(int id, String name, int birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }
}