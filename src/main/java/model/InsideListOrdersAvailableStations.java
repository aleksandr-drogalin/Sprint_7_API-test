package model;

public class InsideListOrdersAvailableStations {
    // класс для десереализации ответа, возвращающего список заказов

    // создаем приваные поля - ключи для объекта ответа
    private String name;
    private String number;
    private String color;

    // геттер и сеттер для name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // геттер и сеттер для number
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    // геттер и сеттер для color
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}