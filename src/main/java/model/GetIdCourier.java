package model;

public class GetIdCourier {
    // класс для десереализации ответа после логина в системе, с целью дальнейшего получения id

    // создаем приваные поля - ключи для объекта ответа после авторизации
    private String id;

    // геттер и сеттер для поля id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}