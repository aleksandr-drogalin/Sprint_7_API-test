package model;

public class CreateCourier {

    // создаем приватные поля - ключи для создания курьера из json
    private String login;
    private String password;
    private String firstName;

    // создаем конструктор с параметрами для создания объекта
    public CreateCourier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    // создаем конструктор без параметров для преобразования объекта в json
    public CreateCourier() {
    }

    // геттер и сеттер для поля login
    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    // геттер и  сеттер для поля password
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    // геттер и  сеттер для firstName
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }
}