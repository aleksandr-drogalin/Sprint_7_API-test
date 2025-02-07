package model;

import java.util.List;

public class ListOrders {
    // класс для десереализации ответа, возвращающего список заказов

    // создаем приваные поля - ключи для объекта ответа
    private List<InsideListOrdersOrders> orders;
    private InsideListOrdersPageInfo pageInfo;
    private List<InsideListOrdersAvailableStations> availableStations;

    // геттер и сеттер для orders
    public List<InsideListOrdersOrders> getOrders() {
        return orders;
    }

    public void setOrders(List<InsideListOrdersOrders> orders) {
        this.orders = orders;
    }

    // геттер и сеттер для pageInfo
    public InsideListOrdersPageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(InsideListOrdersPageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    // геттер и сеттер для availableStations
    public List<InsideListOrdersAvailableStations> getAvailableStations() {
        return availableStations;
    }

    public void setAvailableStations(List<InsideListOrdersAvailableStations> availableStations) {
        this.availableStations = availableStations;
    }
}