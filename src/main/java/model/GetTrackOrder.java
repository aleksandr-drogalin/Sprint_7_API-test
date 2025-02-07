package model;

public class GetTrackOrder {
    // класс для десереализации ответа после оформления заказа, с целью дальнейшей работы с трек-номером

    // создаем приваные поля - ключи для объекта ответа после авторизации
    private String track;

    // геттер и сеттер для поля id
    public void setTrack(String track) {
        this.track = track;
    }

    public String getTrack() {
        return track;
    }
}