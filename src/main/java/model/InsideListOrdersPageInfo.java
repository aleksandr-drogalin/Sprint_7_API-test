package model;

public class InsideListOrdersPageInfo {
    // класс для десереализации ответа, возвращающего список заказов

    // создаем приваные поля - ключи для объекта ответа
    private int page;
    private int total;
    private int limit;

    // геттер и сеттер для page
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    // геттер и сеттер для total
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    // геттер и сеттер для Limit
    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}