package ru.netology.patterns.data;

import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {

    public static String generateDate(int days) {
        // дата через передаваемое кол-во дней в формате ДД.ММ.ГГГГ (как в поле "Календарь")
        String date = LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return date;
    }

    public static String generateCity(String locale) {
        // TODO: добавить логику для объявления переменной city и задания её значения, генерацию можно выполнить
        // с помощью Faker, либо используя массив валидных городов и класс Random
        String[] cities = {"Санкт-Петербург", "Москва", "Астрахань", "Краснодар", "Ярославль", "Саратов", "Нижний Новгород", "Калининград"};
        int rnd = new Random().nextInt(cities.length);
        return cities[rnd];
    }

    public static String generateName() {
        Faker faker = new Faker(new Locale("ru"));
        String name = faker.name().fullName();
        return name;
    }

    public static String generatePhone() {
        Faker faker = new Faker(new Locale("ru"));
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }
}