package model;

public class InsideListOrdersOrders {
    // класс для десереализации ответа, возвращающего список заказов

    // создаем приваные поля - ключи для объекта ответа
    private int id;
    private String courierId;
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private int track;
    private String[] color;
    private String comment;
    private String createdAt;
    private String updatedAt;
    private int status;

    // геттер и сеттер для id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // геттер и сеттер для courierId
    public String getCourierId() {
        return courierId;
    }

    public void setCourierId(String courierId) {
        this.courierId = courierId;
    }

    // геттер и сеттер для firstName
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // геттер и сеттер для lastName
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // геттер и сеттер для address
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // геттер и сеттер для metroStation
    public String getMetroStation() {
        return metroStation;
    }

    public void setMetroStation(String metroStation) {
        this.metroStation = metroStation;
    }

    // геттер и сеттер для phone
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // геттер и сеттер для rentTime
    public int getRentTime() {
        return rentTime;
    }

    public void setRentTime(int rentTime) {
        this.rentTime = rentTime;
    }

    // геттер и сеттер для deliveryDate
    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    // геттер и сеттер для track
    public int getTrack() {
        return track;
    }

    public void setTrack(int track) {
        this.track = track;
    }

    // геттер и сеттер для color
    public String[] getColor() {
        return color;
    }

    public void setColor(String[] color) {
        this.color = color;
    }

    // геттер и сеттер для comment
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    // геттер и сеттер для createdAt
    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    // геттер и сеттер для updatedAt
    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    // геттер и сеттер для status
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}